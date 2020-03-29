import discord
from discord.ext import commands
# We usually import the discord library together with the extension "discord.ext.commands".


client = commands.Bot(command_prefix="!")
# This sets the variable of the bot(i.e client) and it's command prefix.


@client.event  # The event decorator sets the following function as an event.
# Discord.py uses asyncio to define functions.
async def on_ready():  # On_ready is called when the bot is online and ready.
    print("Bot is ready")  # This prints out a message when the deploy of the bot is successful.
# This event is useful because it can confirm whether the bot has no errors during deploy.


client.run("{token}")  # Place your token here so that the bot can login.
