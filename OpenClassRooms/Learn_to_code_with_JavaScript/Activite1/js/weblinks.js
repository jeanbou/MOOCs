// List of links to show. Each link has:
// - a title
// - a URL
// - an author (the person who added it)

// Data
var linkList = [
    {
        title: "Kottke",
        url: "http://kottke.org",
        author: "brett.suggs"
    },
    {
        title: "National Geographic",
        url: "http://www.nationalgeographic.com",
        author: "jessica"
    },
    {
        title: "American Museum of Natural History",
        url: "http://www.amnh.org",
        author: "aurora.nicole"
    }
];

// Add
function addData() {    
    
    var authorElement = document.getElementById("username");
    var titleElement = document.getElementById("info");
    var urlElement = document.getElementById("httpsource");
    
    linkList.push( {"title":titleElement.value,"url":urlElement.value,"author":authorElement.value} );
    showAll();
}

// Create beauty links' entity
function createLinkElement(link) {
    var linktitle = document.createElement("a");
    linktitle.href = link.url;
    linktitle.style.color = "#428bca";
    linktitle.style.textDecoration = "none";
    linktitle.style.marginRight = "5px";
    linktitle.appendChild(document.createTextNode(link.title));

    var linkUrl = document.createElement("span");
    linkUrl.appendChild(document.createTextNode(link.url));

    var titleLine = document.createElement("h4");
    titleLine.style.margin = "0px";
    titleLine.appendChild(linktitle);
    titleLine.appendChild(linkUrl);

    var detailsLine = document.createElement("span");
    detailsLine.appendChild(document.createTextNode("Added by " + link.author));

    var linkDiv = document.createElement("div");
    linkDiv.classList.add("link");
    linkDiv.appendChild(titleLine);
    linkDiv.appendChild(detailsLine);

    return linkDiv;
}

// Show all list of links
function showAll() {
    var content = document.getElementById("content");
    linkList.forEach(function (link) {
        var linkElement = createLinkElement(link);
        content.appendChild(linkElement);
    });
}

// Initialization the fields with the instructions
function initializeFieldInstruction () {

    var authorElement = document.getElementById("username")
    authorElement.value = "Enter Author";

    var titleElement = document.getElementById("info")
    titleElement.value = "Enter Title";

    var urlElement = document.getElementById("httpsource")
    urlElement.value = "Enter URL";    
}


// Check the fields
function validateFields() {

    document.getElementById("username").addEventListener("blur", function (e) {
        
        var authorElement = e.target.value;

        if ( !authorElement.match(/^[a-zA-Z]+$/) || (authorElement.length < 3 ) ) {
            alert ('Author field is improper. Please enter valid author id!');
            return false;
        }
    });

    document.getElementById("info").addEventListener("blur", function (e) {
        
        var titleElement = e.target.value;

        if ( titleElement.length < 5 ) {
            alert ('Title field is improper. Please enter at least 5 characters!');
            return false;
        }
    });

    document.getElementById("httpsource").addEventListener("blur", function (e) {
        
        var urlElement = e.target.value;

        if ( !urlElement.match(/^((https?):\/\/)?[a-zA-Z0-9\-\.]{3,}\.[a-zA-Z]{2,}(\.[a-zA-Z]{2,})?$/) ) {
            alert ('URL is improper. Please enter a valid!');
            return false;
        }
    });
}   

// The beginning of the execution

initializeFieldInstruction();
validateFields();
showAll();

// The end