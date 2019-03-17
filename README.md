# Reorganizing Code To Properly Dispose Of Assets And 'Disposables'

Examples are meant to show an implementation in its simplest form - anything that's not necessary for the demonstration shouldn't be there. 
I've seen hundreds of code examples that invalidate themselves by involving way more than the subject of the demonstration - it becomes a lot 
of work to sort out what's directly important in the implemenation.

That's why I left the master branch of this scene2d example simple - but the fact is, it's exemplifying some pretty bad habits, listed below.

- textures, sounds, and other assets are loaded but never released

- there's an 'object' called 'AppObj' where various members are set up statically, which is a bad practice (especially on Android), and they 
are never disposed, which can result in memory leaks

I like to store spritebatches, stages, sounds, and other objects that need to be accessed from different parts of the game in an object called 
AppObj and then just access them statically like this 'AppObj.stage.batch' for example - well, that's convenient, but Android Studio 
underlines these 'static resources' and says this about them:

> Don't make resources static, unless you take care to properly manage them. Static resources can cause problems on Android, because the 
life-cycle of a static variable is not necessarily the same as the life-cycle of your application.  Note that Kotlin top-level properties and 
properties of object literals and companion objects are compiled to static properties. 

This means that using static members like this is fine on desktop, but on Android there's no guarentee that your static resource will behave 
the same way. Kotlin makes a lot of things static which might not appear that way (especially since the word 'static' doesn't exist in 
kotlin). For example, if you just put a methods and variables all on their own in a file, they are static. 

On top of that, because libgdx is circumventing the java garbage collector, a lot of objects need to be 'disposed of'. While there's no 
apparent penelty for not doing this, in reality the memory isn't getting released. That's what a memory leak is. All you have to do is keep 
track of these 'disposable' types and make sure that you call their 'dispose()' method when you override the dispose() method in your Game or 
Screen.

There is a list of what objects require this in the [libgdx documentation on memory management](https://github.com/libgdx/libgdx/wiki/Memory-management)

While my games only have 1 or 2 stages and spritebatches, there's often a lot more textures and sounds to keep track of. That would become a 
heck of a lot of work making sure every single picture got its dispose() method called. 

The solution for that is 'AssetManager' - basically if you register your textures, sounds, skins, etc in an AssetManager object, you can just 
call the 'dispose()' method on the AssetManager and it takes care of it. Sure, it adds a little overhead in that the AssetManager needs to 
know the type of asset you're getting from it, but in this application I use one AssetManager for all the sounds, one for all the Images etc, 
so I can ignore it. I'm using the 'type-safe assets' approach described here [ktx 
extensions](https://github.com/libktx/ktx/tree/master/assets) so I can refer to each asset by name - for example I can retrieve my explosion 
sound, which is called 'boom.ogg' like this boom().play().

So by keeping all the things that need to be disposed of in App, and passing an instance of App into all of my screens and scenes, I can avoid 
'static resources' and properly dispose of everything.

You'll probably agree that understanding all of this would probably become a barrier of entry for a beginner, and that they'd probably be fine 
just ignoring memory management until they've progressed more. That's how I felt when I was getting started.
