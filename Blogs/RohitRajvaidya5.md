## npm - Node Package Manager

![Nd9pJHZky](/assets/Nd9pJHZky.webp)

npm handles the packages required for a project as well as the records of all [dependency](###Dependency), versions, and other crucial information.

## How to install npm

1. Just go to [Node Js](<[www.google.com](https://nodejs.org/en/)>)
2. Install it with installer of your current os.
3. And npm will install with nodejs.
4. to check whether npm installed you can check npm version with command `npm -v` in your favourite terminal.

## npm benefits

- Pre-installed with node.js
- Easily install package/module in your system.NPM - Node Package Manager

## initialize npm

1. Open Terminal in repository you want to make your project.
2. give command `npm init` to create new package.
3. it takes some details like author , license ,git-repo etc.
4. if you want to continue with default details which is easy and time saving to create package there is command `npm init --yes`.( [How to set default values](#how-to-set-default-values) )
5. after package is created it creates the file with name [package.json](###package.json-file).

## install package

- To install package there is command `npm install package_name --save` . It install package locally in that repo.
- To install package globally (into your whole system) use `npm install package_name -g`.
- `--save` saves the dependency [version](##Version-System) in package.json file.
- And if you use `npm install package_name --save-dev` it will save package as [devDependency](###devDependency).
- To uninstall package there is command `npm uninstall package_name --save`.
- And it is similarly applicable for devDependency.

## Version System

![Version System in npm](/assets/Version%20System%20in%20npm.png)

Generally in npm version is #.#.# this type of arrangement in which # contains numbers from 0 to unlimited.

- Major Version &rarr; It is version which conatin new features than previous version and also it can break the project if use in uncompatible type.
- Minor Version &rarr; This version contains minor updates and features and it can't break the project.

- Patch &rarr; Patch is just some bug fixes and it doesn't break anything.

## npm update

To update the package `npm update package_name` command is use.
There are 4 parameters or flags that denote whole version system and updates which contains in dependencies section in package.json file.

![i3](/assets/i3.png)

==Symbols mention below can see in "dependencies" section after "express" package.==

- ^ &rarr; If you use update command in project it updates only minor version that means major version so much important for the project.
- ~ &rarr; It only updates the patch.
- [No Sign] &rarr; You have to install exact version . e.g. if it shows 4.7.9 you have to install exactly this version. To install exact version you can use `npm install package_name@versionNumber`.
- - &rarr; If there is only '\*' in dependencies it means it updates most latest version of that package (But it is not recommended.)

## Concepts use in this blog

### Dependency

It is package which is essential for running the project. or the package which is use only while production.

### devDependency

devDependency is dependency which is useful for testing and development.

### package.json file

- Contain details like author,license,script etc.
- Contain all the list of dependencies.
- Also contain all the version numbers.
  <br>

## Commands to create and handle packages

This commands are discuss in this blog and some additional commands which can also be useful.

<br>

| Commmand                                      | What it does                                                                  |
| --------------------------------------------- | ----------------------------------------------------------------------------- |
| npm -v                                        | To check version of npm.                                                      |
| npm init                                      | Create new package (With taking all the info)                                 |
| npm init --yes                                | Create package with all the default values                                    |
| npm config set init-author-name "author_name" | Set default author name                                                       |
| npm set init-license "license_name"           | Set license                                                                   |
| npm get init-author-name                      | Get Author Name                                                               |
| npm get init-license                          | Get License                                                                   |
| npm config delete init-author-name            | Delete default author name                                                    |
| npm config delete init-author-name            | Delete default license                                                        |
| npm install package_name --save               | Package will install in project repository and add dependency in package.json |
| npm install package_name --save-dev           | Install package and add it as devDependencies                                 |
| npm uninstall package_name --save-dev         | Remove package from devDependencies                                           |
| npm uninstall package_name --save             | Remove package from dependencies                                              |
| npm update package_name                       | To update the package                                                         |
| npm list                                      | List all the packages in project                                              |
| npm list depth                                | List the packages with minimum detail                                         |
| npm install package_name -g                   | Install package globally(into your system)                                    |
