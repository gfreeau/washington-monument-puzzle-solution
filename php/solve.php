<?php

// Washington monument puzzle

// all cube faces must add up to 555
// there is over 262000+ possible permutations

// http://www.creativecrafthouse.com/index.php?main_page=product_info&products_id=502

$cubes = [
    [95, 30, 25, 60],
    [55, 25, 20, 70],
    [20, 50, 55, 10],
    [45, 55, 65, 15],
    [90, 55, 90, 80],
    [60, 90, 85, 55],
    [45, 95, 15, 55],
    [75, 65, 55, 55],
    [55, 70, 35, 55],
    [55, 55, 60, 75],
];

$face1Permutations = getCubePermutations($cubes, 0);
$face1Permutations = filterIncorrectSums($face1Permutations);

$solutions = [];

foreach($face1Permutations as $permutation) {
    $possibleSolutions = getPossibleSolutions($permutation, $cubes);

    foreach($possibleSolutions as $solution) {
        if (checkSolution($solution) && !array_key_exists(getSolutionHash($solution), $solutions)) {
            $solutions[getSolutionHash($solution)] = $solution;

            echo getCubeOutput($solution) . "\n\n";
        }
    }
}

echo sprintf("Found %d solutions\n", count($solutions));

// function definitions

function getSolutionHash(array $cubes) {
    return md5(json_encode($cubes));
}

function getCubePermutations(array $cubes, $face = 0) {
    $permutations = [];

    $numCubes = count($cubes);

    for ($i = 0; $i < $numCubes; $i++) {
        if ($i === 0 && isset($cubes[0][$face])) {
            $values = [$cubes[0][$face]];
        } else {
            $values = $cubes[$i];
        }

        $permutations = appendCubePermutations($permutations, $values);
    }

    return $permutations;
}

function appendCubePermutations(array $permutations, array $values) {
    if (empty($permutations)) {
        return array_map(
            function ($value) {
                return [$value];
            },
            $values
        );
    }

    $result = [];

    for ($i = 0; $i < count($values); $i++) {
        $tempCopy = $permutations;
        $item = $values[$i];

        for ($j = 0; $j < count($tempCopy); $j++) {
            $tempCopy[$j][] = $item;
        }

        $result = array_merge($result, $tempCopy);

        unset($tempCopy);
    }

    return $result;
}

function filterIncorrectSums(array $permutations) {
    return array_filter($permutations, "hasCorrectSum");
}

function hasCorrectSum(array $cubeFaces) {
    return array_sum($cubeFaces) === 555;
}

function getPossibleSolutions(array $permutation, array $cubes) {
    if (empty($cubes)) {
        return false;
    }

    $solutions = [];

    $numCubes = count($cubes);
    $numFaces = count($cubes[0]);

    // challenge: same number can appear more than once on a single cube

    for ($i = 0; $i < $numCubes; $i++) {
        $value = $permutation[$i];
        $turns = 0;

        while ($turns < $numFaces) {
            if ($cubes[$i][0] == $value) {
                $solutions = appendToPossibleSolutions($solutions, $i, $cubes[$i]);
            }

            $cubes[$i] = turnCubeClockwise($cubes[$i]);

            $turns++;
        }
    }

    return $solutions;
}

function appendToPossibleSolutions(array $solutions, $column, array $cube) {
    if (!is_numeric($column) || empty($cube)) {
        return $solutions;
    }

    if (empty($solutions)) {
        $solutions[] = [$column => $cube];

        return $solutions;
    }

    $result = [];

    for ($i = 0; $i < count($solutions); $i++) {
        $solution = $solutions[$i];

        if (!array_key_exists($column, $solution)) {
            // add the column
            $solution[$column] = $cube;
            $result[] = $solution;

            continue;
        }

        // make a copy and replace the column
        $permutation = $solution;
        $permutation[$column] = $cube;

        // re-add the existing solution and add the new permutation
        $result[] = $solution;
        $result[] = $permutation;
    }

    return $result;
}

function turnCubeClockwise(array $cube) {
    $last = array_pop($cube);
    array_unshift($cube, $last);

    return $cube;
}

function turnCubeCounterclockwise(array $cube) {
    $first = array_shift($cube);
    array_push($cube, $first);

    return $cube;
}

function checkSolution(array $cubes) {
    if (empty($cubes)) {
        return false;
    }

    $numFaces = count($cubes[0]);

    for ($i = 0; $i < $numFaces; $i++) {
        $cubeFaces = array_column($cubes, $i);
        if (!hasCorrectSum($cubeFaces)) {
            return false;
        }
    }

    return true;
}

function getCubeOutput(array $cubes) {
    $output = '';

    foreach($cubes as $cube) {
        $output .= sprintf("[%s]\n", join(', ', $cube));
    }

    return $output;
}
