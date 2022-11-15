Every open source community is different.

Spending years on one open source project means you’ve gotten to know one open source project. Move to a different project, and you might find the vocabulary, norms, and communication styles are completely different.

That said, many open source projects follow a similar organizational structure. Understanding the different community roles and overall process will help you get quickly oriented to any new project.

A typical open source project has the following types of people:

Author: The person/s or organization that created the project
Owner: The person/s who has administrative ownership over the organization or repository (not always the same as the original author)
Maintainers: Contributors who are responsible for driving the vision and managing the organizational aspects of the project (They may also be authors or owners of the project.)
Contributors: Everyone who has contributed something back to the project
Community Members: People who use the project. They might be active in conversations or express their opinion on the project’s direction
Bigger projects may also have subcommittees or working groups focused on different tasks, such as tooling, triage, community moderation, and event organizing. Look on a project’s website for a “team” page, or in the repository for governance documentation, to find this information.

A project also has documentation. These files are usually listed in the top level of a repository.

LICENSE: By definition, every open source project must have an open source license. If the project does not have a license, it is not open source.
README: The README is the instruction manual that welcomes new community members to the project. It explains why the project is useful and how to get started.
CONTRIBUTING: Whereas READMEs help people use the project, contributing docs help people contribute to the project. It explains what types of contributions are needed and how the process works. While not every project has a CONTRIBUTING file, its presence signals that this is a welcoming project to contribute to.
CODE_OF_CONDUCT: The code of conduct sets ground rules for participants’ behavior associated and helps to facilitate a friendly, welcoming environment. While not every project has a CODE_OF_CONDUCT file, its presence signals that this is a welcoming project to contribute to.
Other documentation: There might be additional documentation, such as tutorials, walkthroughs, or governance policies, especially on bigger projects.
Finally, open source projects use the following tools to organize discussion. Reading through the archives will give you a good picture of how the community thinks and works.

Issue tracker: Where people discuss issues related to the project.
Pull requests: Where people discuss and review changes that are in progress.
Discussion forums or mailing lists: Some projects may use these channels for conversational topics (for example, “How do I…“ or “What do you think about…“ instead of bug reports or feature requests). Others use the issue tracker for all conversations.
Synchronous chat channel: Some projects use chat channels (such as Slack or IRC) for casual conversation, collaboration, and quick exchanges.
Finding a project to contribute to
Now that you’ve figured out how open source projects work, it’s time to find a project to contribute to!

If you’ve never contributed to open source before, take some advice from U.S. President John F. Kennedy, who once said, “Ask not what your country can do for you - ask what you can do for your country.”

Contributing to open source happens at all levels, across projects. You don’t need to overthink what exactly your first contribution will be, or how it will look.

Instead, start by thinking about the projects you already use, or want to use. The projects you’ll actively contribute to are the ones you find yourself coming back to.

Within those projects, whenever you catch yourself thinking that something could be better or different, act on your instinct.

Open source isn’t an exclusive club; it’s made by people just like you. “Open source” is just a fancy term for treating the world’s problems as fixable.

You might scan a README and find a broken link or a typo. Or you’re a new user and you noticed something is broken, or an issue that you think should really be in the documentation. Instead of ignoring it and moving on, or asking someone else to fix it, see whether you can help out by pitching in. That’s what open source is all about!

28% of casual contributions to open source are documentation, such as a typo fix, reformatting, or writing a translation.

If you’re looking for existing issues you can fix, every open source project has a /contribute page that highlights beginner-friendly issues you can start out with. Navigate to the main page of the repository on GitHub, and add /contribute at the end of the URL (for example https://github.com/facebook/react/contribute).

You can also use one of the following resources to help you discover and contribute to new projects:

GitHub Explore
Open Source Friday
First Timers Only
CodeTriage
24 Pull Requests
Up For Grabs
Contributor-ninja
First Contributions
SourceSort
A checklist before you contribute
When you’ve found a project you’d like to contribute to, do a quick scan to make sure that the project is suitable for accepting contributions. Otherwise, your hard work may never get a response.

Here’s a handy checklist to evaluate whether a project is good for new contributors.

Meets the definition of open source


Does it have a license? Usually, there is a file called LICENSE in the root of the repository.
Project actively accepts contributions

Look at the commit activity on the main branch. On GitHub, you can see this information on a repository’s homepage.


When was the latest commit?

How many contributors does the project have?

How often do people commit? (On GitHub, you can find this by clicking "Commits" in the top bar.)
Next, look at the project’s issues.


How many open issues are there?

Do maintainers respond quickly to issues when they are opened?

Is there active discussion on the issues?

Are the issues recent?

Are issues getting closed? (On GitHub, click the "closed" tab on the Issues page to see closed issues.)
Now do the same for the project’s pull requests.


How many open pull requests are there?

Do maintainers respond quickly to pull requests when they are opened?

Is there active discussion on the pull requests?

Are the pull requests recent?

How recently were any pull requests merged? (On GitHub, click the "closed" tab on the Pull Requests page to see closed PRs.)
Project is welcoming

A project that is friendly and welcoming signals that they will be receptive to new contributors.


Do the maintainers respond helpfully to questions in issues?

Are people friendly in the issues, discussion forum, and chat (for example, IRC or Slack)?

Do pull requests get reviewed?

Do maintainers thank people for their contributions?
avatarWhenever you see a long thread, spot check responses from core developers coming late in the thread. Are they summarizing constructively, and taking steps to bring the thread to a decision while remaining polite? If you see a lot of flame wars going on, that’s often a sign that energy is going into argument instead of into development.

— @kfogel, Producing OSS

How to submit a contribution
You’ve found a project you like, and you’re ready to make a contribution. Finally! Here’s how to get your contribution in the right way.

Communicating effectively
Whether you’re a one-time contributor or trying to join a community, working with others is one of the most important skills you’ll develop in open source.

avatar[As a new contributor,] I quickly realized I had to ask questions if I wanted to be able to close the issue. I skimmed through the code base. Once I had some sense of what was going on, I asked for more direction. And voilà! I was able to solve the issue after getting all the relevant details I needed.

— @shubheksha, A Beginner’s Very Bumpy Journey Through The World of Open Source

Before you open an issue or pull request, or ask a question in chat, keep these points in mind to help your ideas come across effectively.

Give context. Help others get quickly up to speed. If you’re running into an error, explain what you’re trying to do and how to reproduce it. If you’re suggesting a new idea, explain why you think it’d be useful to the project (not just to you!).

😇 “X doesn’t happen when I do Y”

😢 “X is broken! Please fix it.”

Do your homework beforehand. It’s OK not to know things, but show that you tried. Before asking for help, be sure to check a project’s README, documentation, issues (open or closed), mailing list, and search the internet for an answer. People will appreciate it when you demonstrate that you’re trying to learn.

😇 “I’m not sure how to implement X. I checked the help docs and didn’t find any mentions.”

😢 “How do I X?”

Keep requests short and direct. Much like sending an email, every contribution, no matter how simple or helpful, requires someone else’s review. Many projects have more incoming requests than people available to help. Be concise. You will increase the chance that someone will be able to help you.

😇 “I’d like to write an API tutorial.”

😢 “I was driving down the highway the other day and stopped for gas, and then I had this amazing idea for something we should be doing, but before I explain that, let me show you…“

Keep all communication public. Although it’s tempting, don’t reach out to maintainers privately unless you need to share sensitive information (such as a security issue or serious conduct violation). When you keep the conversation public, more people can learn and benefit from your exchange. Discussions can be, in themselves, contributions.

😇 (as a comment) “@-maintainer Hi there! How should we proceed on this PR?”

😢 (as an email) “Hey there, sorry to bother you over email, but I was wondering if you’ve had a chance to review my PR”

It’s okay to ask questions (but be patient!). Everybody was new to the project at some point, and even experienced contributors need to get up to speed when they look at a new project. By the same token, even longtime maintainers are not always familiar with every part of the project. Show them the same patience that you’d want them to show to you.

😇 “Thanks for looking into this error. I followed your suggestions. Here’s the output.”

😢 “Why can’t you fix my problem? Isn’t this your project?”

Respect community decisions. Your ideas may differ from the community’s priorities or vision. They may offer feedback or decide not to pursue your idea. While you should discuss and look for compromise, maintainers have to live with your decision longer than you will. If you disagree with their direction, you can always work on your own fork or start your own project.

😇 “I’m disappointed you can’t support my use case, but as you’ve explained it only affects a minor portion of users, I understand why. Thanks for listening.”

😢 “Why won’t you support my use case? This is unacceptable!”

Above all, keep it classy. Open source is made up of collaborators from all over the world. Context gets lost across languages, cultures, geographies, and time zones. In addition, written communication makes it harder to convey a tone or mood. Assume good intentions in these conversations. It’s fine to politely push back on an idea, ask for more context, or further clarify your position. Just try to leave the internet a better place than when you found it.

Gathering context
Before doing anything, do a quick check to make sure your idea hasn’t been discussed elsewhere. Skim the project’s README, issues (open and closed), mailing list, and Stack Overflow. You don’t have to spend hours going through everything, but a quick search for a few key terms goes a long way.

If you can’t find your idea elsewhere, you’re ready to make a move. If the project is on GitHub, you’ll likely communicate by opening an issue or pull request:

Issues are like starting a conversation or discussion
Pull requests are for starting work on a solution
For lightweight communication, such as a clarifying or how-to question, try asking on Stack Overflow, IRC, Slack, or other chat channels, if the project has one
Before you open an issue or pull request, check the project’s contributing docs (usually a file called CONTRIBUTING, or in the README), to see whether you need to include anything specific. For example, they may ask that you follow a template, or require that you use tests.

If you want to make a substantial contribution, open an issue to ask before working on it. It’s helpful to watch the project for a while (on GitHub, you can click “Watch” to be notified of all conversations), and get to know community members, before doing work that might not get accepted.

avatarYou’ll learn a lot from taking a single project you actively use, “watching” it on GitHub and reading every issue and PR.

— @gaearon on joining projects

Opening an issue
You should usually open an issue in the following situations:

Report an error you can’t solve yourself
Discuss a high-level topic or idea (for example, community, vision or policies)
Propose a new feature or other project idea
Tips for communicating on issues:

If you see an open issue that you want to tackle, comment on the issue to let people know you’re on it. That way, people are less likely to duplicate your work.
If an issue was opened a while ago, it’s possible that it’s being addressed somewhere else, or has already been resolved, so comment to ask for confirmation before starting work.
If you opened an issue, but figured out the answer later on your own, comment on the issue to let people know, then close the issue. Even documenting that outcome is a contribution to the project.
Opening a pull request
You should usually open a pull request in the following situations:

Submit trivial fixes (for example, a typo, a broken link or an obvious error)
Start work on a contribution that was already asked for, or that you’ve already discussed, in an issue
A pull request doesn’t have to represent finished work. It’s usually better to open a pull request early on, so others can watch or give feedback on your progress. Just open it as a “draft” or mark as a “WIP” (Work in Progress) in the subject line. You can always add more commits later.

If the project is on GitHub, here’s how to submit a pull request:

Fork the repository and clone it locally. Connect your local to the original “upstream” repository by adding it as a remote. Pull in changes from “upstream” often so that you stay up to date so that when you submit your pull request, merge conflicts will be less likely. (See more detailed instructions here.)
Create a branch for your edits.
Reference any relevant issues or supporting documentation in your PR (for example, “Closes #37.”)
Include screenshots of the before and after if your changes include differences in HTML/CSS. Drag and drop the images into the body of your pull request.
Test your changes! Run your changes against any existing tests if they exist and create new ones when needed. Whether tests exist or not, make sure your changes don’t break the existing project.
Contribute in the style of the project to the best of your abilities. This may mean using indents, semi-colons or comments differently than you would in your own repository, but makes it easier for the maintainer to merge, others to understand and maintain in the future.
If this is your first pull request, check out Make a Pull Request, which @kentcdodds created as a walkthrough video tutorial. You can also practice making a pull request in the First Contributions repository, created by @Roshanjossey.

What happens after you submit a contribution
You did it! Congratulations on becoming an open source contributor. We hope it’s the first of many.

After you submit a contribution, one of the following will happen:

😭 You don’t get a response.
Hopefully you checked the project for signs of activity before making a contribution. Even on an active project, however, it’s possible that your contribution won’t get a response.

If you haven’t gotten a response in over a week, it’s fair to politely respond in that same thread, asking someone for a review. If you know the name of the right person to review your contribution, you can @-mention them in that thread.

Don’t reach out to that person privately; remember that public communication is vital to open source projects.

If you make a polite bump and still nobody responds, it’s possible that nobody will respond, ever. It’s not a great feeling, but don’t let that discourage you. It’s happened to everyone! There are many possible reasons why you didn’t get a response, including personal circumstances that may be out of your control. Try to find another project or way to contribute. If anything, this is a good reason not to invest too much time in making a contribution before other community members are engaged and responsive.

🚧 Someone requests changes to your contribution.
It’s common that you’ll be asked to make changes to your contribution, whether that’s feedback on the scope of your idea, or changes to your code.

When someone requests changes, be responsive. They’ve taken the time to review your contribution. Opening a PR and walking away is bad form. If you don’t know how to make changes, research the problem, then ask for help if you need it.

If you don’t have time to work on the issue anymore (for example, if the conversation has been going on for months, and your circumstances have changed), let the maintainer know so they’re not expecting a response. Someone else may be happy to take over.

👎 Your contribution doesn’t get accepted.
Your contribution may or may not be accepted in the end. Hopefully you didn’t put too much work into it already. If you’re not sure why it wasn’t accepted, it’s perfectly reasonable to ask the maintainer for feedback and clarification. Ultimately, however, you’ll need to respect that this is their decision. Don’t argue or get hostile. You’re always welcome to fork and work on your own version if you disagree!
