package alexman.undo;

import java.util.LinkedList;
import java.util.List;

/**
 * A wrapper for implementing undo and redo functionality facilitated by
 * {@code Undoable} objects. After executing an {@code Undoable} it should be
 * added to a History object so that the History can handle all future
 * interactions with it.
 *
 * @param <T> the type of {@code Undoable} object that will be stored
 *
 * @see Undoable
 *
 * @author Alex Mandelias
 */
public class UndoableHistory<T extends Undoable> {

	private final LinkedList<T> past, future;

	/** Creates an empty Undoable History */
	public UndoableHistory() {
		past = new LinkedList<>();
		future = new LinkedList<>();
	}

	/**
	 * Adds an {@code Undoable} to the history without executing it.
	 *
	 * @param undoable the Undoable
	 */
	public void add(T undoable) {
		past.push(undoable);

		// flush the redo history
		if (!future.isEmpty())
			future.clear();
	}

	/**
	 * Undoes the last {@code Undoable}. If there are no {@code Undoables} to be
	 * undone this method does nothing.
	 */
	public void undo() {
		if (canUndo()) {
			final T last = past.pop();
			last.unexecute();
			future.push(last);
		}
	}

	/**
	 * Re-does the last {@code Undoable}. If there are no {@code Undoables} to be
	 * re-done this method does nothing.
	 */
	public void redo() {
		if (canRedo()) {
			final T first = future.pop();
			first.execute();
			past.push(first);
		}
	}

	/** Empties this Undoable History */
	public void clear() {
		past.clear();
		future.clear();
	}

	/**
	 * Returns whether there is an {@code Undoable} to be undone.
	 *
	 * @return {@code true} if there is, {@code false} otherwise
	 */
	public boolean canUndo() {
		return !past.isEmpty();
	}

	/**
	 * Returns whether there is an {@code Undoable} to be redone.
	 *
	 * @return {@code true} if there is, {@code false} otherwise
	 */
	public boolean canRedo() {
		return !future.isEmpty();
	}

	/**
	 * Returns the past part of the history.
	 *
	 * @return a List containing the previously executed Undoables. The first
	 *         {@code Undoable} will be the most recently executed one.
	 */
	public List<T> getPast() {
		return new LinkedList<>(past);
	}

	/**
	 * Returns the future part of the history.
	 *
	 * @return a List containing the previously un-executed Undoables. The first
	 *         {@code Undoable} will be the most recently un-executed one.
	 */
	public List<T> getFuture() {
		return new LinkedList<>(future);
	}
}
