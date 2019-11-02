package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Customer;
import seedu.address.model.task.Task;

/**
 * List delivered task for specified driver
 */
public class ViewCustomerTaskCommand extends Command {

    public static final String COMMAND_WORD = "viewC";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the delivered tasks of the specified customer "
            + "and displays them as a list with index numbers."
            + "\n"
            + "Parameters: " + "INDEX (must be a positive integer)"
            + "[" + PREFIX_CUSTOMER + "CUSTOMER_ID] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CUSTOMER + "1";


    public static final String MESSAGE_SUCCESS = "listed delivered tasks for the specified Customer";

    private int customerId;

    public ViewCustomerTaskCommand(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> currentCustomerList = model.getFilteredCustomerList();
        List<Task> completedTaskList = model.getCompletedTaskList();

        if (customerId > currentCustomerList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        Customer customerToView = currentCustomerList.get(customerId);
        model.viewCustomerTask(customerToView);
        return new CommandResult(String.format(MESSAGE_SUCCESS, customerToView));
    }

    //@Override
    //    public CommandResult execute(Model model) {
    //        requireNonNull(model);
    //        model.updateFilteredPersonList(predicate);
    //        return new CommandResult(
    //                String.format(Messages.MESSAGE_DRIVERS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    //


}
