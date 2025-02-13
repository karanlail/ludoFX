package com.karan.ludofx.model;

/**
 * This class creates token objects.
 */
public class Token {
    private static final int SQUARES_MAX=51;
    private final Color color;
    private int position;
    private TokenState tokenState;
    private final int id;

    public Token(Color color, int id) {
        this.color = color;
        this.id=id;
        this.tokenState=TokenState.BASE;
    }

    public Color getColor() {
        return color;
    }

    TokenState getTokenState() {
        return tokenState;
    }

    private void setTokenState(TokenState tokenState) {
        this.tokenState = tokenState;
    }

    public int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    /**
     * Changes tokenState to ACTIVE and places token on respective starting point (specified in color enum)
     */
    private void activate(){
        setTokenState(TokenState.ACTIVE);
        setPosition(getColor().getStart());
    }

    /**
     * Increases token position by 1
     */
    private void stepForward(){
        setPosition(getPosition()+1);
    }

    /**
     * Increases token position by numberOfSteps by calling stepForward method, unless tokenState is BASE or FINISHED
     * Ensures a circular motion on board path by setting position to 0 after 51.
     * Calls activate if numberOfSteps == 6 and tokenState is BASE
     * @param numberOfSteps
     */
    public void move (int numberOfSteps) {
        if(isBase() && numberOfSteps ==6){
            activate();
            return;
        }
        if (isBase() || isFinished()){
            throw new IllegalStateException();
        }
        for (int i = 0; i < numberOfSteps; i++) {
            /*handle a circular motion*/
            if(getPosition() == SQUARES_MAX && isActive()){
                setPosition(0);
            } else stepForward();

            if(getPosition() == (getColor().getEnd() + 1)) {
                setPosition(0);
                setTokenState(TokenState.HOME);
            }
            //how to act if on home run
            if(isHome()){
                if(getPosition()==6){
                    setTokenState(TokenState.FINISHED);
                }
            }
        }
    }

    public boolean isFinished(){
        return getTokenState()==TokenState.FINISHED;
    }

    public boolean isBase(){
        return getTokenState()==TokenState.BASE;
    }

    public boolean isActive(){
        return getTokenState()==TokenState.ACTIVE;
    }

    private boolean isHome(){
        return getTokenState()==TokenState.HOME;
    }

    /**
     * returns true if token can move with given numberOfSteps
     * @param numberOfSteps
     */
    public boolean movable(int numberOfSteps) {
        if (isFinished()) return false;
        else if (isBase()) return numberOfSteps == 6;
        else if (isHome()) return (position + numberOfSteps) <= 6;
        else return true;
    }

    /**
     * sets tokenState to BASE
     */
    public void captured(){
        setTokenState(TokenState.BASE);
    }

    @Override
    public String toString() {
        return "Token{" +
                "color=" + color +
                ", position=" + position +
                ", tokenState=" + tokenState +
                ", id=" + id +
                '}';
    }
}
