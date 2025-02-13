package com.karan.ludofx.model;

public enum Color {
    /**
     * This enum ensures code clarity as every player in game is assigned a color and consequently every token has a color too.
     * The path for tokens on the board depends on the color.
     * Each color has starting point and home path entrance point specified for token use.
     */
    GREEN(0,50),
    BLUE(13,11),
    YELLOW(26,24),
    RED(39,37);
    private final int start;
    private final int end;

    Color(int start, int end) {
        this.start=start;
        this.end=end;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    /**
     * This method allows for proper player placement on the board, as two players must play on opposite colors
     * @param playerNumber
     * @param numberOfPlayers
     * @return
     */
    public static Color colorToBoardPositioning (int playerNumber, int numberOfPlayers){
        if(playerNumber >4 || playerNumber <1) throw new IllegalArgumentException();
        if (numberOfPlayers==2){
            switch (playerNumber) {
                case 1 -> {
                    return Color.GREEN;
                }
                case 2 -> {
                    return Color.YELLOW;
                }
            }
        }
        switch (playerNumber){
            case 1 -> {
                return Color.GREEN;
            }
            case 2 -> {
                return Color.BLUE;
            }
            case 3 -> {
                return Color.YELLOW;
            }
            case 4 -> {
                return Color.RED;
            }
        }
        return null;
    }
}
