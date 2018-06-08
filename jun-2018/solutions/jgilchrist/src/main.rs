extern crate petgraph;
extern crate itertools;

use petgraph::prelude::*;
use petgraph::graph::node_index;
use petgraph::algo;
use itertools::Itertools;
use std::fs;
use std::env;

type Coord = (i32, i32);

fn to_row(row_str: &str) -> Vec<bool> {
    row_str
        .split(',')
        .map(|cell| if cell == "1" { true } else { false })
        .collect()
}

#[inline]
fn convert_to_node_index(a: &Coord, size: i32) -> i32 {
    a.0 * size + a.1
}

fn is_open_space(rows: &Vec<Vec<bool>>, a: &Coord) -> bool {
    !rows[a.0 as usize][a.1 as usize]
}

#[inline]
fn is_valid_coord(a: &Coord, size: i32) -> bool {
    a.0 >= 0 && a.0 < size && a.1 >= 0 && a.1 < size
}

fn check_solvable(rows: &Vec<Vec<bool>>) -> bool {
    let size = rows.len() as i32;

    let all_nodes: Vec<Coord> = (0..size)
        .cartesian_product(0..size)
        .collect();

    let open_nodes: Vec<&Coord> = all_nodes
        .iter()
        .filter(|n| is_open_space(rows, n))
        .collect();

    let mut graph = Graph::<i32, (), Undirected>::new_undirected();

    let mut start_node_option: Option<NodeIndex<u32>> = None;
    let mut end_node_option: Option<NodeIndex<u32>> = None;

    for node in &all_nodes {
        let index = convert_to_node_index(node, size);
        let n = graph.add_node(index);

        if index == 1 {
            start_node_option = Some(n);
        }

        if index == size * size - 2 {
            end_node_option = Some(n);
        }
    }

    for node in &open_nodes {
        let neighbors_coords = vec![
            (node.0-1, node.1),
            (node.0+1, node.1),
            (node.0,   node.1-1),
            (node.0,   node.1+1)
        ];

        let neighbors: Vec<&Coord> = neighbors_coords
            .iter()
            .filter(|n| is_valid_coord(n, size))
            .collect();

        for neighbor in neighbors {
            if is_open_space(rows, neighbor) {
                graph.add_edge(
                    node_index(convert_to_node_index(node, size) as usize),
                    node_index(convert_to_node_index(neighbor, size) as usize),
                    ()
                );
            }
        }
    }

    let start_node = start_node_option.unwrap();
    let end_node = end_node_option.unwrap();

    algo::has_path_connecting(&graph, start_node, end_node, None)
}

fn main() {
    let args: Vec<String> = env::args().collect();

    if args.len() != 2 {
        panic!("No input file specified");
    }

    let filename = &args[1];

    let maze_str = fs::read_to_string(filename).expect("Unable to read the input file");
    let mut maze_lines = maze_str.lines();

    let number_of_lines = maze_lines
        .next().unwrap()
        .parse::<i32>().unwrap();

    let mut results: Vec<i32> = vec![];

    for _ in 0..number_of_lines {
        let number_of_maze_rows = maze_lines
            .next().unwrap()
            .parse::<i32>().unwrap();

        let mut rows = vec![];

        for _ in 0..number_of_maze_rows {
            rows.push(to_row(maze_lines.next().unwrap()));
        }

        let is_solvable = check_solvable(&rows);

        results.push(is_solvable as i32);
    }

    let mut result = results.iter().join("\n");
    result.push_str("\n");

    fs::write("output.txt", result).expect("Unable to write to output file");
}
