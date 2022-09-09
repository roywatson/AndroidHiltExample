### "AndroidHiltExample" example project by:

##       Roy Watson

rwatson@dela.com

www.dela.com

This is a simple example that strips out a lot of generally accepted architectural components and other proclaimed best practices. It is using the minimum of dependencies, frameworks and libraries in order to avoid obscuring the illustration of using the Hilt dependency injection library. 

##### Branches:

"start" branch is the raw templated project created by Android Studio Chipmunk | 2021.2.1 Patch 2. This is supplied for comparison purposes. ***It appears you are currently on this branch. To view the demo code you should switch to the "complete" branch.***

"complete" branch is the example/tutorial application where the "magic"😀 happens. 

##### This example demonstrates/teaches:

- Hilt dependency injection

##### This example also includes/uses:

- Kotlin
- View Model

### How to best use this demo:

The starting point of this app was the template for a new project in Android Studio Chipmunk | 2021.2.1 Patch 2 called “Fragment + ViewModel” and using Kotlin. You can create an empty project based on that template and compare it to this project to discern what needs to be done to get your project started with Hilt dependency injection. 

If you have experience with Dagger, most of this will look and feel very familiar. That's because Hilt is a wrapper around Dagger.

The gist of using Hilt is that we need to define a *@Module* that *@Provides* instances of Classes where and when we need them. This is advantageous because we can *@Inject* instances of our dependencies into the code where we need them. The '@' sign preceding Module, Provides and Inject are there because those are annotations provided by the hilt libraries and processors.

In order to use Hilt in our App we need to create a subclass of Application, in our examples called *RoysApplication*, which is annotated with *@HiltApplication*. Please note that in *RoysApplication* we provide a getter to return the instance of *RoysApplication*. I use the singular "the" because in Android, the Application class is instantiated as a singleton, there will only be one Application object.

Next let's create some simple classes that we can use to inject. What I created for that purpose is in the package named *com.delasystems.androidhiltexample.data*. I created a simple class called *RoysFiler* which has but one method, *getFile()* , which simply returns the apps *filesDir*. I chose this because it is an Android API call that requires a *context*. I also created a very simple data repository called *RoysRepository*. It too has just one method called *getFileDirectory()*. It calls the *getFile()* method in *RoysFiler*. I call the repositories *getFileDirectory()* method from my ViewModel. I chose this configuration because we have a hierarchy of dependancies. RoysFiler needs a context which is not available to plain old Kotlin classes, that is those not derived from an Android  Activity or a very few other Android classes. RoysRepository in turn depends on RoysFiler, MainViewModel depends on RoysRepository and finally, MainFragment depends on MainViewModel.

We could obtain a context in MainFragment and pass it down to MainViewModel as a parameter to *getFilesPath()*, who  could pass it along to RoysRepository.getFileDirectory() who could could pass it to *RoysFiler.getFile()* who would now have it available to use to get *filesDir*.

Instead, we directly *@Inject* a context into the constructor of *RoysFiler*. Likewise, I directly inject a *RoysFiler* instance into the constructor of *RoysRepository* and on up the dependency tree.

But where do these instances we are injecting coming from? They come from a set of functions we will now create. These functions are called Providers. You can see the Providers I created in the file RoysModule in the *com.delasystems.androidhiltexample.di* package. The first provider in that file is *providesRoysRepository(filer: RoyFiler)* which returns an instance of *RoysRepository* whenever an instance is needed. It is also annotated as *@Singleton*. This means that Hilt will always return the same instance, it won't create a new instance each time.

Likewise *providesRoyFiler(context: Context)* provides a single instance of *RoysFiler* whenever one is needed. 

Finally, *provideContext()* provides an *ApplicationContext* when desired. Notice that it is not annotated as *@Singleton*. That is because Application is always a singleton so the annotation is not needed.

Thank you and if you have ny comments or questions please feel free to contact me at rwatson@dela.com

## License:

    Copyright (C) 2022 Roy Watson
    
    Permission is hereby granted, free of charge, to any person obtaining a copy of this
    software and associated documentation files (the "Software"), to deal in the Software 
    without restriction, including without limitation the rights to use, copy, modify, merge, 
    publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons 
    to whom the Software is furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all copies 
    or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
    PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
    FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
    TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
    OR OTHER DEALINGS IN THE SOFTWARE.

