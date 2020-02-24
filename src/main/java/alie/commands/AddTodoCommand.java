package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.exceptions.InvalidCmdException;
import alie.task.ToDo;

//Input format: <task type> <task name>
public class AddTodoCommand extends Command {
    private final ToDo taskToAdd;

    public static final String COMMAND_KEYWORD = "todo";
    public String ADD_TODO_ACK =
            INDENTATION + "Got it. I've added this task:" + System.lineSeparator() +
            MORE_INDENTATION + "%1$s" + System.lineSeparator() +
            INDENTATION + "Now you have %2$s tasks in the list.";

    public AddTodoCommand(String[] splitCommand) throws InvalidCmdException {
        try {
            taskToAdd = new ToDo(splitCommand[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCmdException("Description cannot be missing!");
        }
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage)
            throws InvalidCmdException {
        if (taskToAdd.getName().equals("")) {
            throw new InvalidCmdException("Description cannot be missing!");
        }
        taskLists.addNewTask(taskToAdd);
        return new CommandResult(String.format(ADD_TODO_ACK, taskToAdd.getTaskInfo(),
                taskLists.getNumOfTasks()));
    }
}