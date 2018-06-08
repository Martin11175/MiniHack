.PHONY: default
default: compile time

.PHONY: compile
compile:
	@cargo build --release
	@cp ./target/release/jun-2018 .

.PHONY: time
time:
	@time -p ./jun-2018 ../../mazes.txt

