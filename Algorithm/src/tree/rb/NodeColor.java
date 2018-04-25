package tree.rb;

enum NodeColor {
	
	RED("Red"),BLACK("Black");
    private final String color;

    private NodeColor(final String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
	
}