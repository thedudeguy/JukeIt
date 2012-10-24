![Alt text](http://i.minus.com/ibv0o73mpincih.png "JukeIt")

------------------------------------

JukeIt (formerly JukeBukkit) allows users to craft blank discs, called Obsidyiscs. You can burn these discs with music of your liking, music that is hosted on the internet somewhere, and play the music on your obsidyiscs in jukeboxes, also crafted by players.

[![Download](http://i.minus.com/i0xywzd0kbM7K.png "Download")](http://chrischurchwell.com/products/jukebukkit/)

[Screenshots](https://github.com/thedudeguy/JukeIt/wiki/Screenshots) | [Wiki](https://github.com/thedudeguy/JukeIt/wiki) | [Source](https://github.com/thedudeguy/JukeIt)

### The Info You Want: 

[How to use JukeBukkit](https://github.com/thedudeguy/JukeIt/wiki/How-to-Use-JukeBukkit)

[JukeBukkit Crafting Recipes](https://github.com/thedudeguy/JukeIt/wiki/Recipes)

[Configuration](https://github.com/thedudeguy/JukeIt/wiki/Configuration)

[Setting up the Web Server](https://github.com/thedudeguy/JukeIt/wiki/Setting-up-the-Web-Server)

[Permissions](https://github.com/thedudeguy/JukeIt/wiki/Permissions)

## Features

* Craft discs called Obsidyiscs
* Attach .ogg's, wav's, or mp3's to an Obsidyisc in which that perticular disc will play.
* All players within range of a jukebox can hear an Obsidyisc playing.
* Build numerous types of jukeboxes with different ranges.
* Built in Server to host Audio files on your own server!
* Nice GUI to allow for users to choose from the server music list when creating their burned discs.
* Allow your users to upload their music to the server via the web gui.
* Craft multiple types of jukeboxes
* Craft jukebox modifiers such as speakers which increase audible range.
* Craft Needle modifiers such as Blaze Needle which increase audible range by 30%.
* Activate jukeboxes via redstone.

## Requirements

JukeIt is built and tested against

* SpoutPlugin atleast build 1369
* SpoutCraft atleast build  1833
* CraftBukkit 1.3.2-R2.0

## Installation

* JukeIt _Requires_ SpoutPlugin to run.
* This does not require users to use Spoutcraft to be able to log in to the server. However, players without Spoutcraft will be unable to see jukeboxes or hear them, and will only see Flint.
* Install by placing jukeit.jar into your bukkit server's plugin folder.
* Restart Server

*Important If you plan to use the Web Server*

* After restarted, you *must* edit the config.yml for JukeIt.
* Set the minecraftServerHostname config option to your server's Domain name, or IP Address, that your user's use to connect to your server.
* Make sure to set the webServerPort to an open port on your server, and the appropriate port forwarding has been established so that users can connect to the webServer.
* Restart Server 

## Upgrading from version 2.1.0

* As always, back up everything.
* Shut down your Minecraft Server
* Rename the "JukeBukkit" data folder to "JukeIt"
* Download and place the JukeIt plugin jar into your servers plugins folder.
* If you use permissions, you will need to adjust any permissions set for JukeIt. All permission nodes have been renamed from jukebukkit to jukeit.
* Start server.

Previously placed JukeBukkit blocks will all dissapear, and become stone and such. You will need to re-place the blocks manually. I apologize if this inconveniences you.


## Support

* [Wiki](https://github.com/thedudeguy/JukeIt/wiki)
* [Issues](https://github.com/thedudeguy/JukeIt/issues)

## Credits

[Credits](https://github.com/thedudeguy/JukeIt/wiki/Credits)

### Change Log

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
* No longer supporting crafting with all wood types, allthough many still work.
* Added custom texture support (WIP)
* Re-encoded the provided music to a lower bitrate.
* Added robots.txt to the web server (to prevent indexing)
* now using my MeshIt obj loader libary
* removed the old disc yml importer.
* Improved Burn GUI Navigation.
* Disc on a Stick is now a weapon that deals random damage and takes random durability hits.
