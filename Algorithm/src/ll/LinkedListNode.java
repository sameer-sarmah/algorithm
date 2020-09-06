package ll;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkedListNode<T> {

	private T value;
	private LinkedListNode<T> next;
	private LinkedListNode<T> previous;
	@Override
	public String toString() {
		return "LinkedListNode [value=" + value + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((previous == null) ? 0 : previous.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		LinkedListNode other = (LinkedListNode) obj;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (next != other.next)
			return false;
		if (previous == null) {
			if (other.previous != null)
				return false;
		} else if (previous != other.previous)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
}
