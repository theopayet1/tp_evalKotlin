# TP Évaluation Kotlin Android — Waifu (waifu.im)

Projet Android Jetpack Compose (Kotlin) réalisé dans le cadre d’une évaluation “1 journée”.
Objectif : application simple, propre, lisible et sans warnings.

---

## Fonctionnalités

- Écran **Splash** puis navigation automatique vers **Home**
- Écran **Home** :
  - récupère **10 images portrait** via l’API **waifu.im**
  - affiche les images en **grille**
  - gère **loading / erreur / retry**
- **Thème** : Clair / Sombre / Système (menu dans la TopBar)
  - feedback : **son + vibration** lors du changement de thème
- **Variante de langue** :
  - `values/strings.xml` (FR)
  - `values-en/strings.xml` (EN) — libellés du menu thème

---

## API (waifu.im)

- Base URL : `https://api.waifu.im/`
- Endpoint utilisé : `GET /search`
- Paramètres :
  - `included_tags=waifu`
  - `is_nsfw=false`
  - `orientation=PORTRAIT`
  - `limit=10`

Client HTTP : **Ktor** (engine **CIO**) + `kotlinx.serialization`.

---

## Architecture (couches)

L’application est organisée en 3 couches principales :

### UI (`ui/`)
- Écrans Compose (ex : `HomeScreen`)
- ViewModels (MVVM) + gestion d’état (UDF)
- **La UI ne dépend jamais de la couche data directement** : UI → domain → data

### Domain (`domain/`)
- Modèles métier (ex : `WaifuImage`)
- Interfaces de repository (ex : `WaifuRepository`)
- Use cases (ex : `GetPortraitWaifusUseCase`)

### Data (`data/`)
- DTO (`@Serializable` + `@SerialName`) : format API
- Remote (Ktor) : appels réseau
- Implémentations de repository + mapping DTO → domain

---

## Injection de dépendances (DI)

- Utilisation de **Koin** dans `di/AppModule.kt` :
  - `HttpClient` Ktor
  - `WaifuApi`
  - `WaifuRepository`
  - `UseCase`
  - `HomeViewModel`

---

## Navigation

- Navigation Compose avec une sealed class `Destination` (routes centralisées)
- Une extension `NavController.navigateAndClearBackStack(...)` est utilisée pour les cas type “Splash → Home”
  afin d’éviter un retour arrière sur l’écran Splash.

---

## Managers “système” (hardware)

Les accès matériels sont centralisés et ne sont pas appelés directement depuis les Composables.

- `system/SoundManager` : joue un son lors du changement de thème
- Extension `Context.vibrateClick()` : vibration courte lors du changement de thème
- Déclenchement uniquement via `ThemeMenuViewModel` (pas dans la UI)

---

## Ressources et conventions

- Nommage des resources : **snake_case**
- Textes externalisés via `stringResource(...)`
- Variante de langue :
  - `res/values/strings.xml`
  - `res/values-en/strings.xml`

---

## Structure des packages (résumé)

