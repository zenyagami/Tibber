# Fun code for tibber

- Android Compose
- ViewModel
- MVVM with clean architecture (app/domain/data layers)
- Dependency injection with hilt
- Some unit testing with flow (turbine library) and mockito
- apollo graphql

## Improvements
*  For now there is an error message in case the list have an error, also when the list is empty it should display an empty state view.

## Notes
The app is trying to keep the branding colors, but also trying to make "material" ready and dark mode, with some minor tweaks in the theme is easy to switch to dark mode and even dark/ light auto.

The Device details is in a new activity, this could be a fragment but for now was easier make this in an Activity to avoid handling recomposition of the list and avoid the list being refreshed and not preserving the position.  but this can be changed easily.

The connect disconnect is a dummy loading

