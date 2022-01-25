package alexman.undo;

/**
 * An Interface for objects that can be executed and potentially un-executed.
 * These objects should store enough state in order to be able to fully reverse
 * their actions when unexecuted. Because the {@code unexecute} method is
 * declared as {@code default}, this interface may also be used as a Functional
 * Interface.
 *
 * @author Alex Mandelias
 */
@FunctionalInterface
public interface Undoable {

	/** Executes the Undoable */
	void execute();

	/** Un-does the Undoable. The default implementation does nothing. */
	default void unexecute() {}
}
