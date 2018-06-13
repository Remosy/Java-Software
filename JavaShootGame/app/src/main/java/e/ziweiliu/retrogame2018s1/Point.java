package e.ziweiliu.retrogame2018s1;

// by Ying Mun Chin, Yangyang Xu, and Ziwei Liu 2018
// This program is intended as an academic project for submission to COMP2100

public class Point { // coordinate for in-game entities
    float x;
    float y;

    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public float Distance(Point p){ // check distance between two points
        float dx = x - p.x;
        float dy = y - p.y;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    public String toString() {
        return "Point is at (" + x + "," + y + ")";
    }
}
