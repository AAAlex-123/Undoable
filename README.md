# Undoable
Provides the foundations necessary upon to which create an undo/redo system.

### Motivation
Encapsulate the logic behind storing a sequence of Commands while providing the
ability to undo and redo them with ease. Said Commands must implement the
Undoable interface which provides a uniform way to execute and then un-execute
them.

### Implementing the Undoable interface
Classes that implement this interface must store enough context information when
created and executed so that they can also be unexecuted successfully. It can be
reasonably assumed that the same instance of Undoable will not be executed twice
in succession. A new instance should instead be created and executed, since they
represent two fundamentally different Commands that simply do the same thing.

### Using the Undoable History
After implementing the logic behind executing and undoing a Command, it's fairly
straightforward to use a History object to manage the sequence of Commands. A
newly created Command shall be executed and then added to the History. The
History itself doesn't execute the Undoable when added since it may throw a
Runtime Exception the first time it is executed. Therefore, the Command is first
executed and then added to the History. Afterwards the caller need not care
about the Command. To undo, and then possibly redo, the Command, simply call the
undo and redo methods of the History and it will take care of managing the
sequence of Commands. When undoing, the unexecute method is called and when
redoing, the execute method is called again.

The reason that no hook is provided when undoing and redoing (as opposed to
adding/executing) is because it's reasonably assumed that no exception will be
thrown and that these processes will be completed without error. This is because
in order to undo the application goes from a known state (the latest one) to
another known state (the previous one) so it can safely do so. The same goes
with redoing; since the Command has successfully been executed once, all further
re-executions should be identical to the first one.
