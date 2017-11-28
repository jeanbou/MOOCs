/* 
Activité 1 by Jean
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



// Create a link entity for one triple data point
function createLinkElt(link) {
    var linktitle = document.createElement("a");
    linktitle.href = link.url;
    linktitle.style.color = "#428bca"; // defined by specification
	linktitle.style.marginRight = "5px"; // Jean's choice
	linktitle.appendChild(document.createTextNode(link.titre));

    var linkUrl = document.createElement("span");
    linkUrl.appendChild(document.createTextNode(link.url));

    var titleLine = document.createElement("h3"); // Jean's choice
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



// Show all elements of list
function showContentForAllElts() {
    var content = document.getElementById("contenu");
    //var styleBody = getComputedStyle(content,null);
    listeLiens.forEach(function (link) { // run cycle for all triples of links
        var linkElt = createLinkElt(link);
        content.appendChild(linkElt);
    });
}

showContentForAllElts(); // form the list
