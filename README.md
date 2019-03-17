# LibGdx/Ktx Example Of Scene2d Actions and Sequences

![cutscene01](.github/cutscene01.png?raw=true)

If you've ever thought to yourself that your game needs a cutscene or intro sequence, Libgdx Actions is something you'll want to look at. If you're new to scene2d, allow for me to explain.

- Scene2d is an optional framework that is part of Libgdx which allow for you to add 'Actors' to a 'Stage'. The advantage of this is that by adding your actors to a stage, and then calling the stage's act and draw methods in your game loop, all actors will automatically have their act and draw methods called.

- Beyond that, Actors can be easily faded in/out, translated or rotated by adding Actions to the actor. To chain actions one after the other synchronously (running one after the other instead of at the same time), you can use a sequence.

- A whole collection of ui widgets like Window, Button, and Label all extend Actor, so making some text fade in on the screen is as simple as instantiating the right class, adding the fadeIn action to it, and adding the actor to the stage.

- A 'Group' is a collection of Actors, and if you add a bunch of actors to a group, then add the group to a Stage, all of those actors will have their act and draw methods called.

- LibKtx has some really helpful syntax helpers that make it so you can add Actors to a Stage, an Action to an Actor, or add Actions to a sequence by using the += operator.

This example shows a title screen and tells a short story using some Groups that represent different pieces of the story. I think it's smart to split up all the actions in your cutscene into chunks like this, just because it could potentially get pretty complicated if you tried to jam your whole cutscene into one series of actions.

![cutscene02](.github/cutscene02.png?raw=true)  

[image credit to mixmasterangel](https://www.deviantart.com/mixmasterangel/art/Ellen-Ripley-436035869)

Keep in mind that if you want to trigger an event that isn't necessarily an Action, you can always add 

```kotlin
Actions.run { println("call any method from here") } 
```

and add that to an Actor or sequence.

Another thing you might want to do is use some sprites in your cutscene - this project has an example of that with the alien sprite in the 2nd 'scene'. Sprite does not extend Actor, but there's no reason why you can't create a class that extends Actor and have a Sprite as a member. In order to make your Actions apply to your Sprite you'll want to transfer the position, size, and other aspects from the Actor to the Sprite in your overridden act and draw functions.

```kotlin
// in act
sprite.setSize(this.width, this.height)
sprite.setPosition(this.x, this.y)

// in draw
sprite.setAlpha(this.alpha)
sprite.draw(batch)
```

# Note

This example is supposed to show how to create a little example in a simple way, and I am not cleaning up after myself like I should (in libgdx, you should dispose of all of the sounds, pictures, and other assets).

I created a separate branch in this project called ['asset_management'](https://github.com/jojomickymack/alien-cutscene/tree/asset_management) where I use [AssetManager](https://github.com/libgdx/libgdx/wiki/Managing-your-assets) to load everything in an organized way and dispose of it when the game ends. I use some techniques from [libktx's 'asset' extensions](https://github.com/libktx/ktx/tree/master/assets), and I also do away with the AppObj, which has static resources (convenient way to access things globally, but Android Studio will underline it). 

You'll probably prefer the version without asset management if you're just getting started with libgdx/ktx - but when you're getting ready to publish your game you'll want to make sure your assets are getting disposed properly and you're not leaking memory.
