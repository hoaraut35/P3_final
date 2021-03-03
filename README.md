# Entrevoisins

Ce dépôt contient une mini-application Entrevoisins.

Une application qui permet à des personnes d’un même quartier de se rendre des petits services : 
* garde d’animaux,
* petit bricolage,
* troc d’objets,
* cours particuliers,
* de nombreuses options s’offrent aux utilisateurs !

![start image](https://github.com/hoaraut35/p3_test/blob/master/Pr%C3%A9sentation/Images/neighbour_list.PNG)

Pour commencer à travailler sur ce projet, suivez les étapes ci-dessous.

## Android studio : Télécharger puis ouvrir le projet

1. - Cliquer sur le bouton "code puis "Download ZIP"
2. - Extraire le fichier ZIP dans le dossier de votre choix  
3. - Démarrer Android Studio
4. - Cliquer sur Open Project
5. - Ouvrir le projet depuis de le dossier sélectionné à l'étape 2

### Aperçu des défis rencontrés lors de ce projet

1La mise en page du layout détail, la superposition du FAB
	J'ai testé le LinearLayout, le RelativeLayout, le ConstraintLayout puis finalement le CoordinatorLayout, cela m'a donné la possibilité de pratiquer ces différents ViewGroup et d'en découvrir quelques particularités.

2 La gestion des événements de clics sur deux RecyclerView
	L’utilisation d’une interface de Callback et son implémentation au niveau des fragments pour la gestion des clics sur les différentes pages du ViewPager
3 Le passage d'un objet voisin de la première activité à la seconde par un Intent
	La recherche d’information sur le passage d’un objet d’une activité vers une seconde activité. J'ai finalement utilisé la librairie GSON pour le passage du voisin plutôt que Serializable, Parcelable car je l'ai trouvé pratique.
4 L'utilisation générale des RecyclerView, Adapter, ViewPager
	Beaucoup de recherche d’information sur le rôle du FragmentPagerAdapter, le ViewPager, le RecyclerView, le ListView
5 Butter Knife
	Comprendre le fonctionnement de Butter Knife pour caster les vues dans le code. L’utilisation de ce système dans le cadre du projet. 

6 EventBus
	Comprendre le fonctionnement d’EventBus dans le cadre de ce projet, mise en pratique de son utilisation pour la gestion des événements de clics. 

7 Mise en place des tests instrumentalisés et unitaires
	Je me suis inspiré des tests du projet 2, complété par les cours et les documentations sur Internet (JUnit, Espresso).
8 Git, GitHuB
	Compréhension du fonctionnement de base de Git et mise en ligne sur un dépôt distant GiHub.