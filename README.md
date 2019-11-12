# Movie Selector
This project uses TheMovieDB API to retrieve the 10 most recent movies in theatres and display them in a circle selector. When you click on the individual ovals you go into a new screen providing even more information on the movie. This project was created with an MVVM architecture, databinding, and has a full unit test suite. The project is complete with PRs for every major code change and self review on those PRs.

## Libraries Used
This project used the following Android libraries:
- Retrofit: https://square.github.io/retrofit/
- Picasso: https://square.github.io/picasso/
- Mockito: https://site.mockito.org/
- WheelView: https://github.com/LukeDeighton/WheelView

## FAQ
Q: Why did you pick MVVM and databinding?

A: Databinding is one of the most powerful tools introduced into Android. It allows the use of the observable pattern for keeping the UI up-to-date and always matching the data in any given ViewModel, Presenter, or Controller. MVVM was used since it pairs so well with databinding and LiveData. It also allows for easier unit testing as well, because there is no dependency on the View. Finally, MVVM removes the tight coupling that MVC/MVP contains. In MVC every activity must have a presenter. In MVVM viewModels can be reused for as many activities as seen fit. This is becase the viewModel holds no reference to the View. While I didn't re-use any viewModels in my implementation of MVVM (mainly for clarity), it was always an option.

Q: Why is x method in y class not tested?

A: Most likely, the class itself or the methods in the classes didn't have justifiably testable code. Writing tests for android components, setters/getters, or library components are unnecesary because they already have unit tests written for them.

Q: What improvements would you make if you had more time with this project?

A: I would have liked to seen images on the wheel selector instead of ovals and their entry number. However I don't think it was possible with the library I was using. This is discussed further in the remaining open PR.
