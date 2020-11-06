import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// interface for LinkedList and ArrayList
public interface list<E> extends ActionListener{
public void add(celestialBody a);
public void delete(int pos);
public E get(int x);
public void printAll();
public void draw(Graphics g);
public void actionPerformed(ActionEvent e);
public void paintComponent(Graphics g);
}
