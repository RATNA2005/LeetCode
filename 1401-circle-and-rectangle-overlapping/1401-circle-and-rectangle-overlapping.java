class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter,
                                int x1, int y1, int x2, int y2) {

        // Find the closest point on the rectangle to the circle's center
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        // Distance from circle center to that closest point
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;

        // Check whether closest point lies inside/on the circle
        return dx * dx + dy * dy <= radius * radius;
    }
}