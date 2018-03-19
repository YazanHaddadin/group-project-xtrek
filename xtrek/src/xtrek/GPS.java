package xtrek;

public enum GPS {
    NORTH("+"), SOUTH("-"), EAST("+"), WEST("-");

    String val = "";

    GPS(String val) {
        this.val = val;
    }
}
