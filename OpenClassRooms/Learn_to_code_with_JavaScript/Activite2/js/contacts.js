/*  IB
Activity: Contact manager
*/

// Initialization of array of contacts
var contacts = [];

// Person object
var Person = {
    // Initialize the person
    initPerson: function (firstname, lastname) {
        this.firstname = firstname;
        this.lastname = lastname;        
    },

    // Print person
    printContact: function () {
        console.log("Last name: " + this.lastname + ", first name: " + this.firstname + "\n");
    }    
};

// Prints out the menu
function menu() {
	console.log("1: List contacts\n2: Add contacts\n0: Quit (or press Enter or Breakspace & Enter)");
}

// List all contact
function listAllContacts() {
	console.log("Here\'s the list of all your contacts\n");
	contacts.forEach(function (contact) {
      contact.printContact();
	});
}

// Add new contact
function addNewContact() {    
    var lastName = prompt("Please enter new contact LAST NAME:");
    var firstName = prompt("Please enter new contact FIRST NAME:");
    
    if (/^[a-zA-Z]+$/.test(lastName) && /^[a-zA-Z]+$/.test(lastName)) {
        var newContact = Object.create(Person);
        newContact.initPerson(firstName, lastName);
        contacts.push(newContact);
        console.log ("Contact added\n");    
    }
    else 
    {
        console.log ("LAST or FIRST or both names is/are not a proper string, Contact HAS NOT been added! Try again!\n");   
    }
}


// Two persons (mandatory contacts), hard coded
var contact1 = Object.create(Person);
contact1.initPerson("John", "Smith");
contacts.push(contact1);

var contact2 = Object.create(Person);
contact2.initPerson("Jane", "Doe");
contacts.push(contact2); 

// Initializing of the menu option control variable
var menuOption = 1;
// Initial greetings
console.log ("Welcome to you contacts manager!");

while (menuOption) // main cycle
{
	menu();
    menuOption = Number(prompt("Please enter menu option (IT MUST BE INTEGER NUMBER):"));

    if (Number.isNaN(menuOption) || (menuOption == undefined) || (menuOption == null)) {
        menuOption = -1; // assigning an error code and force user to read again the manu and try again
    }

    // The input is made, choose the options
    switch(menuOption) {
        case 0:
            // correct exit
            break;
        case 1:
            listAllContacts();
            break;
        case 2:
            addNewContact();
            break;
        break;
        default:
            console.log("ERROR :: No such menu option, try again\n");
    } // the end of main switch

} // the end of while and program