public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    NORTH_WEST,
    NORTH_EAST,
    SOUTH_WEST,
    SOUTH_EAST;
    public String toString() {
        switch (this) {
            case NORTH: return "Północ";
            case SOUTH: return "Południe";
            case WEST: return "Zachód";
            case EAST: return "Wschód";
            case NORTH_WEST: return "Półnoscy Zachód";
            case NORTH_EAST: return "Północny Wschód";
            case SOUTH_WEST: return "Południowy Zachód";
            case SOUTH_EAST: return "Południowy Wschód";
            default: return null;
        }
    }
    public MapDirection next(){
        switch (this){
            case SOUTH: return SOUTH_WEST;
            case SOUTH_WEST: return WEST;
            case WEST: return NORTH_WEST;
            case NORTH_WEST: return NORTH;
            case NORTH: return NORTH_EAST;
            case NORTH_EAST: return EAST;
            case EAST: return SOUTH_EAST;
            case SOUTH_EAST: return SOUTH;
            default: return null;
        }
    }
    public Vector2d toUnitVector(){
        switch (this){
            case NORTH: return new Vector2d(0,1);
            case EAST: return new Vector2d(1,0);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            case SOUTH_WEST: return new Vector2d(-1,-1);
            case NORTH_WEST: return new Vector2d(-1,1);
            case NORTH_EAST: return new Vector2d(1,1);
            case SOUTH_EAST: return new Vector2d(1,-1);
            default: return null;
        }
    }
}
