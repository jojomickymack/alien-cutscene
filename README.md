# LibGdx/Ktx Example Of Scene2d Actions and Sequences

If you've ever thought to yourself that your game needs a cutscene or intro sequence, Libgdx Actions is something you'll want to look at. If you're new to scene2d, allow for me to explain.

- Scene2d is an optional framework that is part of Libgdx which allow for you to add 'Actors' to a 'Stage'. The advantage of this is that by adding your actors to a stage, and then calling the stage's act and draw methods in your game loop, all actors will automatically have their act and draw methods called.

- Beyond that, Actors can be easily faded in/out, translated or rotated by adding Actions to the actor. To chain actions one after the other synchronously (running one after the other instead of at the same time), you can use a sequence.

- A whole collection of ui widgets like Window, Button, and Label all extend Actor, so making some text fade in on the screen is as simple as instantiating the right class, adding the fadeIn action to it, and adding the actor to the stage.

- A 'Group' is a collection of Actors, and if you add a bunch of actors to a group, then add the group to a Stage, all of those actors will have their act and draw methods called.

- LibKtx has some really helpful syntax helpers that make it so you can add Actors to a Stage, an Action to an Actor, or add Actions to a sequence by using the += operator.

This example shows a title screen and tells a short story using some Groups that represent different pieces of the story. I think it's smart to split up all the actions in your cutscene into chunks like this, just because it could potentially get pretty complicated if you tried to jam your whole cutscene into one series of actions.

Keep in mind that if you want to trigger an event that isn't necessarily an Action, you can always add Actions.run { println("call any method from here") } and add that to an Actor or sequence.
