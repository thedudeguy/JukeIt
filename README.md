![Alt text](http://i.minus.com/ibv0o73mpincih.png "JukeIt")

------------------------------------

## Free Version

JukeIt (formerly JukeBukkit) allows users to craft blank discs, called Obsidyiscs. You can burn these discs with music of your liking, music that is hosted on the internet somewhere, and play the music on your obsidyiscs in jukeboxes, also crafted by players.

Go [Here](http://chrischurchwell.com) to download the Pro version of JukeIt.

[Screenshots](https://github.com/thedudeguy/JukeIt-Free/wiki/Screenshots) | [Wiki](https://github.com/thedudeguy/JukeIt-Free/wiki) | [Source](https://github.com/thedudeguy/JukeIt-Free)

### The Info You Want: 

[How to use JukeIt](https://github.com/thedudeguy/JukeIt-Free/wiki/How-to-Use-JukeIt)

[JukeIt Crafting Recipes](https://github.com/thedudeguy/JukeIt-Free/wiki/Recipes)

[Configuration](https://github.com/thedudeguy/JukeIt-Free/wiki/Configuration)

[Setting up the Web Server](https://github.com/thedudeguy/JukeIt-Free/wiki/Setting-up-the-Web-Server)

[Permissions](https://github.com/thedudeguy/JukeIt-Free/wiki/Permissions)

## Features

* Craft discs called Obsidyiscs
* Attach .ogg's, wav's, or mp3's to an Obsidyisc in which that particular disc will play.
* All players within range of a jukebox can hear an Obsidyisc playing.
* Build numerous types of jukeboxes with different ranges.
* Activate jukeboxes via redstone.

### Upgrade to Pro for even more features, such as:
* Event more jukeboxes with even greater ranges!
* Built in Server to host Audio files on your own server!
* Allow your users to upload their music to the server via the web gui.
* Craft jukebox modifiers such as speakers which increase audible range.
* Craft Needle modifiers such as Blaze Needle which increases audible range by 30%.
* Use Custom Texture Packs
* Loopable Music with the Repeater block!

## Requirements

JukeIt is built and tested against

* SpoutPlugin at least build 1414
* SpoutCraft at least build  1938
* CraftBukkit 1.4.7-R0.1

## Installation

* JukeIt _Requires_ SpoutPlugin to run.
* This does not require users to use Spoutcraft to be able to log in to the server. However, players without Spoutcraft will be unable to see jukeboxes or hear them, and will only see Flint.
* Install by placing jukeit.jar into your bukkit server's plugin folder.
* Restart Server

*Important If you plan to use the Web Server* (Pro Version Only)

* After restarted, you *must* edit the config.yml for JukeIt.
* Set the minecraftServerHostname config option to your server's Domain name, or IP Address, that your user's use to connect to your server.
* Make sure to set the webServerPort to an open port on your server, and the appropriate port forwarding has been established so that users can connect to the webServer.
* Restart Server 

## Upgrading from JukeIt 2 to JukeIt 3

* As always, back up everything.
* Shut down your Minecraft Server
* Download and place the JukeIt plugin jar into your servers plugins folder.
* Start server.

JukeIt 3.0 Has a new disc system. You will need to transfer the discs to the new system. I tried to make this as easy as possible:
* Log in to your server as OP.
* user the command: '/jukeit upgradediscs'. The old disc data will be converted and spawned in the world in front of you. You may need to have some storage space ready if you have a lot of discs!
* When you are satisfied, you can optionally use the command '/jukeit removeolddiscs' to clean up the old disc data from the server. The old disc data will be permanantly deleted, so be careful.

## Support

* [Wiki](https://github.com/thedudeguy/JukeIt-Free/wiki)
* [Issues](https://github.com/thedudeguy/JukeIt-Free/issues)

## Credits

[Credits](https://github.com/thedudeguy/JukeIt-Free/wiki/Credits)

### Change Log

#### Version 3.0.0

* Fixed the repeater chip orientation.
* Fixed client crash when changing the time on the repeater chip
* Repeater Chip drops the correct item.
* Can no longer place the repeater chip block form, must use the item form.
* Revamped the disc system. Burned discs no longer display in the creative menu.

#### Version 2.2.2

* Fixes table generation for dbs other than sqlite.

#### Version 2.2.1

* Fixed Axis Errors with the Repeater Chip Block.
* Added Plugin Metrics

#### Version 2.2.0

* Name Changed to JukeIt
* Record Player Indicator Light now reflects whether the Record Player is receiving redstone power.
* Added new block: Repeater Chip (with GUI) - For music looping.
* Added a GUI for the Record Player.
* Added console only command playjuke.
* Fixed some NPE's.
* Fixed project resource character encoding to UTF-8.
* Fixed Speaker Wire Placement errors when placing next to other custom blocks.
* Changed Speaker Wire Base Block.
* Changed commands to load last, hopefully working better with Dynmap.
* Changed the way db tables are created.
* Changed the disc scratch sound effect to not play when powering up with redstone.
* Changed all permission nodes from jukebukkit to jukeit
* Changed command prefix to /jukeit, or /juke
* Removed the old texture cache workaround.

#### Version 2.1.0

* Updates for 1.3.2
* No longer supporting crafting with all wood types, although many still work.
* Added custom texture support (WIP)
* Re-encoded the provided music to a lower bitrate.
* Added robots.txt to the web server (to prevent indexing)
* now using my MeshIt obj loader libary
* removed the old disc yml importer.
* Improved Burn GUI Navigation.
* Disc on a Stick is now a weapon that deals random damage and takes random durability hits.
