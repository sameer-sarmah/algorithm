package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//5,3,2
//4,2,1
public class BoxStacking {
//b2 can sit on b1
    
private boolean canSitOn(Box b1,Box b2)
{
    if (b1.getArea() > b2.getArea() && (b1.getWidth()>b2.getWidth()) &&(b1.getLength()>b2.getLength()))
        return true;
    else
        return false;
}

private List<Box> createCombination(Box box)
{
  List<Box> list=new ArrayList<>();
  list.add(box);
  list.add(new Box(box.getLength(),box.getHeight(),box.getWidth()));
  list.add(new Box(box.getWidth(),box.getHeight(),box.getLength()));
  return list;
}

    public int findMaxHeight(List<Box> boxes) {
        List<Box> allCombination = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            allCombination.addAll(createCombination(boxes.get(i)));
        }
        allCombination.sort(new BoxComparator());
        System.out.println(allCombination);
        int boxOnbox[]=new int[allCombination.size()];
        for(int i=0;i<boxOnbox.length;i++)
            boxOnbox[i]=i;
        Integer heightArr[]=new Integer[allCombination.size()];
        for(int i=0;i<heightArr.length;i++)
            heightArr[i]=allCombination.get(i).getHeight();
        for (int i = 0; i<allCombination.size(); i++) {
            for (int j = i+1; j <allCombination.size(); j++) {
              if(canSitOn(allCombination.get(i),allCombination.get(j)))
              {if(heightArr[j]<heightArr[i]+allCombination.get(j).getHeight())
                  {
                  boxOnbox[j]=i;  
                  heightArr[j]=heightArr[i]+allCombination.get(j).getHeight();
                  }
              }
            }
        }
        
        return Collections.max(Arrays.asList(heightArr));

    }

public static void main(String [] a)
{
    List<Box> boxes=new ArrayList<>();
    boxes.add(new Box(2,3,5));
    boxes.add(new Box(1,2,4));
    System.out.println(new BoxStacking().findMaxHeight(boxes));
}

}

class Box {
   private int height, width, length;

    
    
    public Box(int height, int width, int length) {
        super();
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getArea() {
        return length * width;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + length;
        result = prime * result + width;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Box other = (Box) obj;
        if (height != other.height)
            return false;
        if (length != other.length)
            return false;
        if (width != other.width)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Box [height=" + height + ", width=" + width + " length=" + length + "]";
    }

    
}

class BoxComparator implements Comparator<Box> {

    @Override
    public int compare(Box b1, Box b2) {
        if (b1.getArea() > b2.getArea())
            return -1;
        else if (b1.getArea() < b2.getArea())
            return 1;
        else
            return 0;
    }

}