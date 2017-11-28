/* 

Activité 3
Done by Jeanbou

*/


// Main part of code

// Part 1 :: When you read content from removed API
var contenuElt = document.getElementById("contenu");

ajaxGet("https://oc-jswebsrv.herokuapp.com/api/liens", function (reponse) { // Call API JSO source via ajaxGet
    var listeLiens = JSON.parse(reponse);
	listeLiens.forEach(function (lien) { 	// Form the list from obtained via API remote data, 
						// note that we don't have JSON data structure inside this file, the power of API!
    		var lienElt = creerElementLien(lien);
    		contenuElt.appendChild(lienElt);
	});
});

// Part 2 :: When you append new instance of content to removed API

var ajoutLienElt = document.getElementById("ajoutLien");
    
    // Manage creation of new field
    ajoutLienElt.addEventListener("click", function () {
    
    // With a hint
    var auteurElt = creerElementInput("Entrez votre nom", 20); // Jean's choice of fields
    var titreElt = creerElementInput("Entrez le titre du lien", 40);
    var urlElt = creerElementInput("Entrez l'URL du lien", 40);

    var ajoutElt = document.createElement("input");
    ajoutElt.type = "submit";
    ajoutElt.value = "Envoyer";

    var formAjoutLien = document.createElement("form");
    formAjoutLien.appendChild(auteurElt);
    formAjoutLien.appendChild(titreElt);
    formAjoutLien.appendChild(urlElt);
    formAjoutLien.appendChild(ajoutElt);

    var p = document.querySelector("p");

    // Replace le bouton d'ajout par le formulaire d'ajout
    p.replaceChild(formAjoutLien, ajoutLienElt);

    // Add new link
    formAjoutLien.addEventListener("submit", function (e) {

	e.preventDefault(); // Block the publication of new form

        // Here I use more strong checker of URL format, thus you cannot input in the field 1234 (without .com or .fr)
 	// Because it's not an url 1234 even ifit will add http in front as requested by specification to exercises
        var url = doubleCheckAndConstructURL(urlElt.value);

        // Strict following of the original JSON link (lien) related structure
        var newLien = {
            titre: titreElt.value,
            url: url,
            auteur: auteurElt.value
        };

        var newLienElt = creerElementLien(newLien);
        contenuElt.insertBefore(newLienElt, contenuElt.firstChild);

        // Replace the formulaire fields by button
        p.replaceChild(ajoutLienElt, formAjoutLien);

        // Creation of info instance linked to div
        var infoElt = document.createElement("div");
        infoElt.classList.add("info");
        infoElt.style.fontSize = "18px"; // Jean's choice
        infoElt.style.marginBottom = "15px"; // Jean's choice
        infoElt.style.backgroundColor = "powderblue"; // Jean's choice (not precesely specify)

        infoElt.textContent = "Le lien \"" + newLien.titre + "\" a bien été ajouté.";
        p.insertBefore(infoElt, ajoutLienElt);

        // Pause
        setTimeout(function () {
            p.removeChild(infoElt);
        }, 2000); // Specified by requirements

	// Sent the filtered correct input to the API server, somewhere remotly
	ajaxPost("https://oc-jswebsrv.herokuapp.com/api/lien", newLien,
    		function (reponse) {
        		// Note: Console is used not for fun, but to show the professor that API accept my data-json instance and not reject it
        		console.log("Nouveaux Lien Element : " + JSON.stringify(newLien) + " a été envoyé au serveur");
    		},
    		true // yes isJson
	);
    });
});


// AJAX GET & POST subfunction well described in the course

// Classic AJAX GET function from the 3rd part of the course used to call API resource located remotly
function ajaxGet(url, callback) {
    var req = new XMLHttpRequest();
    req.open("GET", url);
    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 400) {
            callback(req.responseText);
        } else {
            console.error(req.status + " " + req.statusText + " " + url);
        }
    });
    req.addEventListener("error", function () {
        console.error("Erreur réseau avec l'URL " + url);
    });
    req.send(null);
}

// Classsic AJAX POST function used in course, here I use the complicated but more generic version for JSON structure

function ajaxPost(url, data, callback, isJson) {
    var req = new XMLHttpRequest();
    req.open("POST", url);
    req.addEventListener("load", function () {
        if (req.status >= 200 && req.status < 400) {
            callback(req.responseText);
        } else {
            console.error(req.status + " " + req.statusText + " " + url);
        }
    });
    req.addEventListener("error", function () {
        console.error("Erreur réseau avec l'URL " + url);
    });
    if (isJson) {
        req.setRequestHeader("Content-Type", "application/json");
        data = JSON.stringify(data);
    }
    req.send(data);
}
// The end of AJAX related sub-function


// The list of help function

// For the given link source, create an element function
function creerElementLien(lien) {
    var titreElt = document.createElement("a");
    titreElt.href = lien.url;
    titreElt.style.color = "#428bca"; // Specified by previous requirements
    titreElt.style.textDecoration = "none";
    titreElt.style.marginRight = "5px"; // Jean's choice (specification is silent here)
    titreElt.appendChild(document.createTextNode(lien.titre));

    var urlElt = document.createElement("span");
    urlElt.appendChild(document.createTextNode(lien.url));

    var ligneTitreElt = document.createElement("h4"); // Jean's choice
    ligneTitreElt.style.margin = "0px";
    ligneTitreElt.appendChild(titreElt);
    ligneTitreElt.appendChild(urlElt);

    var ligneDetailsElt = document.createElement("span");
    ligneDetailsElt.appendChild(document.createTextNode("Ajouté par " + lien.auteur));

    // Create div
    var divLienElt = document.createElement("div");
    divLienElt.classList.add("lien");
    divLienElt.appendChild(ligneTitreElt);
    divLienElt.appendChild(ligneDetailsElt);

    return divLienElt;         
}

// Create and send a type input
function creerElementInput(placeholder, taille) {
    var inputElt = document.createElement("input");
    inputElt.type = "text";
    inputElt.setAttribute("placeholder", placeholder);
    inputElt.setAttribute("size", taille);
    inputElt.style.marginRight = "10px"; // Jean's choice
    inputElt.style.borderColor = "aquamarine";  // Jean's choice
    inputElt.style.backgroundColor = "snow";  // Jean's choice
    inputElt.setAttribute("required", "true");
    return inputElt;
}

function doubleCheckAndConstructURL(url) {

  // More stronger URL type of input checker, found on StackOverflow
  if ( !url.match(/^((https?):\/\/)?[a-zA-Z0-9\-\.]{3,}\.[a-zA-Z]{2,}(\.[a-zA-Z]{2,})?$/) ) {
      alert ('Votre URL est incorrect');
      Runtime.getRuntime().exit(n)	// Exit for wrong URL +
					// Hacker Trick's, because the teacher configed Appache TomCat version 7 in a way that it doesn't accept
					// Runtime. call, but my goal will be achieved by blocking the input
					// If you don't belive it; check it empirically as me by typing in URL 12315 and press add button
  }

  // Requested by specification replacement
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
