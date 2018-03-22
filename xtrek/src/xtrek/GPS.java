package xtrek;

public enum GPS {
    NORTH("+"), SOUTH("-"), EAST("+"), WEST("-");

    final String val;

    GPS(String val) {
        this.val = val;
    }
}
