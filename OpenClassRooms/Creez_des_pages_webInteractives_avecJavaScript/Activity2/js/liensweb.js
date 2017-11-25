/* 
Activité 2 by Jean Bou
*/

// Liste des liens Web à afficher. Un lien est défini par :
// - son titre
// - son URL
// - son auteur (la personne qui l'a publié)

var listeLiens = [
    {
        titre: "So Foot",
        url: "http://sofoot.com",
        auteur: "yann.usaille"
    },
    {
        titre: "Guide d'autodéfense numérique",
        url: "http://guide.boum.org",
        auteur: "paulochon"
    },
    {
        titre: "L'encyclopédie en ligne Wikipedia",
        url: "http://Wikipedia.org",
        auteur: "annie.zette"
    }
];


// The end of given part, Jean's code

// Jean's The begining of the RUN Main part

var content = document.getElementById("contenu");
	listeLiens.forEach(function (link) { // run cycle for all triples of links
	    var linkElement = createLinkElement(link);
	    content.appendChild(linkElement);
	});

var buttonToShowForm = document.getElementById("showForm");
var inputForm = document.getElementById("inputForm");
var spanElement = document.getElementById("added");

buttonToShowForm.addEventListener("click", function() {
	inputForm.style.display = "block";
	buttonToShowForm.style.display = "none";
	spanElement.textContent = "";
});

// The end of main part of the code


// The list of sub-functions used in main fucntion

function createLinkElement(link) {
    var linktitle = document.createElement("a");
    linktitle.href = link.url;
    linktitle.style.color = "#428bca"; // defined by specification
    linktitle.style.textDecoration = "none";
    linktitle.style.marginRight = "5px";  // Jean's choice (not specified in specification)
    linktitle.appendChild(document.createTextNode(link.titre));

    var linkUrl = document.createElement("span");
    linkUrl.appendChild(document.createTextNode(link.url));

    var titleLine = document.createElement("h4"); // Jean's choice (not specified in specification)
    titleLine.style.margin = "0px";
    titleLine.appendChild(linktitle);
    titleLine.appendChild(linkUrl);

    var detailsLine = document.createElement("span");
    detailsLine.appendChild(document.createTextNode("Ajouté par " + link.auteur));

    var linkDiv = document.createElement("div");
    linkDiv.classList.add("lien");
    linkDiv.appendChild(titleLine);
    linkDiv.appendChild(detailsLine);

    return linkDiv;
}


function addNewLink() {
  var newTitle = inputForm.elements.titre.value;
  var newAuthor = inputForm.elements.auteur.value;
  var newUrl = checkAndConstructURLTitreAuthor(inputForm.elements.url.value,newTitle,newAuthor);
  
  listeLiens.push( // pushing triples of data
    {
      titre: newTitle,
      url: newUrl,
      auteur: newAuthor
    }
  );

  var newLink = listeLiens[listeLiens.length-1];
  var linkElement = createLinkElement(newLink);
  content.insertAdjacentElement("afterbegin", linkElement); // Usage global variable
  spanElement.textContent = "Le lien "+newTitle+"a été bien ajouté"; // As specified
  spanElement.style.color = "CCFFFF"; 	// Light blue, almost like in specification, not principal, 
					// Jean's choice, just to show that I can manipulate it
  spanElement.style.fontSize = "x-large";

  inputForm.style.display = "none";

  buttonToShowForm.style.display = "block";

  // Re-run for all
  for (var i = 0; i < inputForm.elements.length; i++) {
    inputForm.elements[i].value = "";
  }
  // Time out
  setTimeout(function() { spanElement.textContent = "";
  }, 2000); // Like in specification

}


function checkAndConstructURLTitreAuthor(url,titre,auteur) {

  if ( isStrBlank(auteur) ) {
      alert ('Votre Auteur (Nom) est vide');
      Runtime.getRuntime().exit(n) // Exit Block wrong input
  }  


  if ( isStrBlank(titre) ) {
      alert ('Votre Titre est vide');
      Runtime.getRuntime().exit(n) // Exit
  }    


  if ( !url.match(/^((https?):\/\/)?[a-zA-Z0-9\-\.]{3,}\.[a-zA-Z]{2,}(\.[a-zA-Z]{2,})?$/) ) {
      alert ('Votre URL est incorrect');
      Runtime.getRuntime().exit(n) // Exit for wrong URL
  }


  var newUrl;
  var urlRegex1 = /http:\/\/.+/;
  var urlRegex2 = /https:\/\/.+/;
  if (urlRegex1.test(url) || urlRegex2.test(url)) {
    newUrl = url;
  } else {
    newUrl = "http://" + url;
  }

  return newUrl;

}


function isStrBlank(str) {
    return (!str || /^\s*$/.test(str));
}