package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import ui.pages.LeftMenuPage;
import ui.pages.MainPage;
import ui.pages.NotebookPage;
import ui.pages.NotesPage;

/**
 * Created with IntelliJ IDEA.
 * User: jeancarlorodriguez
 * Date: 11/14/15
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Note {
    MainPage mainPage;
    LeftMenuPage leftMenuPage;
    NotebookPage notebookPage;
    NotesPage notesPage;
    String name;
    public Note()
    {
        mainPage = new MainPage();
    }
    @When("^I create a note \"([^\\\"]*)\" for notebook \"([^\\\"]*)\"$")
    public void ICreateANote(String noteName,String notebookName)
    {
        leftMenuPage = mainPage.getLeftMenu();
        leftMenuPage.goToNewNotePage()
                .createNote(noteName,notebookName);

    }
    @Then("^the note \"([^\\\"]*)\" is in the note list of \"([^\\\"]*)\" notebook$")
    public void theNoteIsInTheNoteListOfTheNotebook(String noteName,String notebookName)
    {
        notebookPage = new NotebookPage();
        boolean actualResult = notebookPage.isNoteExist(noteName);
        boolean expectedResult = true;
        Assert.assertEquals(actualResult,expectedResult);
    }

    @When("^I delete the note \"([^\\\"]*)\"$")
    public void iDeleteANoteFromTheNoteList(String noteName)
    {
        mainPage.getLeftMenu().goToNotesPage().goToDeleteNoteConfirmationPage(noteName).confirm();
        name = noteName;
    }
    @Then("^the note \"([^\\\"]*)\" is not displayed in the list of notes$")
    public void theNoteIsNotInTheList(String noteName)
    {
        boolean actualResult = notesPage.theNoteWasDeleted(noteName);
        boolean expectedResult = true;
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Then("^a message confirming that a note was deleted is displayed$")
    public void aMessageConfirmingThatANoteWasDeleted()
    {
        notesPage = new NotesPage();
        String actualResult = notesPage.getDeleteMessage();
        String expectedResult = name+" "+"moved to Trash.";
        Assert.assertEquals(actualResult,expectedResult);
    }

    @When("^I sort the list of notes by \"([^\\\"]*)\"$")
    public void iSortTheListOfNotesBy(String sortBy)
    {
        NotebookPage notebookPage = new NotebookPage();
        notebookPage.chooseSortOption(sortBy);
    }

    @Then("^the \"([^\\\"]*)\" should be at the beginning of the list of notes$")
    public void theNewNoteShouldBeAtTheBeginningOfTheList(String noteName)
    {
        NotebookPage notebookPage = new NotebookPage();
        String actualResult = notebookPage.getFirstNote();
        String expectedResult = noteName;
        Assert.assertEquals(actualResult,expectedResult);
    }

}
