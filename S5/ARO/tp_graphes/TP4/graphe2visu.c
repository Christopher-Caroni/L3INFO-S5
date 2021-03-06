#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "graphe.h"

/* Couleurs */
typedef enum {ROUGE=0, BLEU=1, VERT=2} tCouleur;
typedef tCouleur tTabCouleurs[MAX_SOMMETS];

/*
graphe: le graphe a parcourir
tabCouleurs: le tableau ou stocker les Couleurs
couleur: la couleur à stocker
num_exclu: le numero du sommet a exclure
*/
void colorieSauf(tGraphe graphe, tTabCouleurs tabCouleurs, tCouleur couleur, int nom_exclu)
{
  int i;
  int nbSommets;

  nbSommets = grapheNbSommets(graphe);
  for (i=0;i<nbSommets;i++)
  {
    if (i != nom_exclu)
    {
      tabCouleurs[i] = couleur;
    }
  }
}


void parcours_profondeur(tGraphe graphe, char *nom_depart)
{
  tNumeroSommet s;
  tPileSommets pile;
  /*
  tabCouleurs[i] correspond à la couleur du sommet i
  */
  tTabCouleurs tabCouleurs;

  tNumeroSommet x;
  int i;
  int nbVoisins;
  tNumeroSommet y;
  int possedeVoisinBleu;
  tNomSommet nomSommet;

  s = grapheChercheSommetParNom(graphe, nom_depart);
  pile = pileSommetsAlloue();

  colorieSauf(graphe, tabCouleurs, BLEU, s);

  tabCouleurs[s] = VERT;
  pileSommetsEmpile(pile, s);

  while (!pileSommetsEstVide(pile))
  {
    x = pileSommetsTete(pile);
    possedeVoisinBleu = 0;

    nbVoisins = grapheNbVoisinsSommet(graphe, x);
    for (i=0;i<nbVoisins;i++)
    {
      y = grapheVoisinSommetNumero(graphe, x, i);
      if (tabCouleurs[y] == BLEU)
      {
        possedeVoisinBleu = 1;
        tabCouleurs[y] = VERT;
        pileSommetsEmpile(pile, y);
        grapheRecupNomSommet(graphe, y, nomSommet);
        printf("%s\n", nomSommet);
      }
    }

    if (possedeVoisinBleu == 0)
    {
      tabCouleurs[x] = ROUGE;
      /*
      c'est forcément x qui est dépilé puisqu'on n'a pas touché à la pile quand possedeVoisinBleu == 0
      */
      pileSommetsDepile(pile);
    }
  }

  pileSommetsLibere(pile);

}

int main(int argc, char **argv)
{
  tGraphe graphe;

  if (argc != 3) {
    halt("Usage : %s <fichier_graphe> <nom_sommet_depart>\n", argv[0]);
  }

  graphe = grapheAlloue();
  grapheChargeFichier(graphe, argv[1]);

  parcours_profondeur(graphe, argv[2]);

  grapheLibere(graphe);
  return 0;
}
