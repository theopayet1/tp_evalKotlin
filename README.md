# Waifu  
Kotlin • Android • Jetpack Compose

![Kotlin](https://img.shields.io/badge/Kotlin-2.0-blueviolet)
![Android](https://img.shields.io/badge/Android-API%2029+-brightgreen)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-blue)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20%2F%20MVVM-orange)
![License](https://img.shields.io/badge/License-Educational-lightgrey)

---

## Présentation

**Waifu** est une application Android moderne développée en **Kotlin** avec **Jetpack Compose**.  
Elle permet de consulter une sélection de **waifus en format portrait** à partir de l’API publique **waifu.im**.

Le projet met l’accent sur :

- la qualité logicielle,
- une architecture claire et maintenable,
- la séparation stricte des responsabilités,
- une expérience utilisateur simple et compréhensible.

Ce projet a été réalisé dans le cadre d’une **évaluation Kotlin Android (1 journée)**.

---

## Fonctionnalités

- Écran **Splash** avec redirection automatique vers l’écran principal
- Écran **Home**
  - récupération de 10 images portrait via l’API
  - affichage en grille
  - gestion des états : chargement / erreur / retry
- Gestion du **thème**
  - Clair / Sombre / Système
  - menu dans la TopBar
  - feedback utilisateur : son et vibration
- Variante de **langue**
  - Français (par défaut)
  - Anglais (`values-en`)

---

## API utilisée

- Nom : waifu.im  
- URL : https://api.waifu.im/
- Endpoint : `/search`
- Paramètres utilisés :
  - `included_tags=waifu`
  - `is_nsfw=false`
  - `orientation=PORTRAIT`
  - `limit=10`

Client HTTP : **Ktor (engine CIO)**  
Sérialisation : **kotlinx.serialization**

---

## Architecture

L’application suit une architecture en couches avec séparation stricte des responsabilités.

### data
- DTO représentant le format de l’API
- Appels réseau via Ktor
- Implémentation des repositories
- Mapping DTO vers modèles métier

### domain
- Modèles métier
- Interfaces de repository
- Use cases représentant les actions métier

### ui
- Écrans Jetpack Compose
- ViewModels (MVVM + UDF)
- Navigation centralisée
- Composants UI réutilisables

La couche UI ne dépend jamais directement de la couche data.

---

## Design system et composants

- Thème Material 3 personnalisé
- Couleurs et typographies centralisées
- Composants réutilisables :
  - `WaifuTopBar`
  - `PrimaryButton`
  - `MainScaffold`
  - Helpers de layout (CenteredBox, Spacer, etc.)

Une bibliothèque de composants est documentée afin de garantir la cohérence visuelle et la maintenabilité.

---

## Injection de dépendances

- Utilisation de **Koin**
- Dépendances injectées :
  - HttpClient Ktor
  - API distante
  - Repository
  - Use case
  - ViewModel

La configuration est centralisée dans un module unique (`appModule`).

---

## Navigation

- Navigation Compose
- Routes centralisées via une sealed class `Destination`
- Extension `NavController.navigateAndClearBackStack(...)` pour les cas Splash → Home
- Backstack nettoyée afin d’éviter un retour arrière vers l’écran Splash

---

## Accès aux fonctionnalités système

- `SoundManager` : gestion centralisée du son
- Extension `Context.vibrateClick()` : vibration courte pour le feedback utilisateur
- Les accès hardware sont déclenchés uniquement depuis les ViewModels, jamais depuis les Composables

---

## Ressources et conventions

- Nommage des resources en `snake_case`
- Textes externalisés via `stringResource`
- Variante de langue :
  - `res/values/strings.xml`
  - `res/values-en/strings.xml`

---

## Qualité et bonnes pratiques

- Aucune logique réseau dans la UI
- DTO distincts des modèles métier
- Asynchronisme géré via coroutines (`suspend`)
- Zéro warning Gradle / lint / manifest
- Code commenté avec des KDoc pour faciliter la compréhension et la maintenance

---

## Lancement du projet

1. Ouvrir le projet dans Android Studio
2. Synchroniser Gradle
3. Lancer l’application sur un émulateur ou un appareil Android
