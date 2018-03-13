[![Build Status](https://travis-ci.org/Get-Reqd/CACI-Utility.svg?branch=master)](https://travis-ci.org/Get-Reqd/CACI-Utility)

# Get-Reqd GitHub Workflow  
I **highly recommend** that you follow this workflow very closely until you feel completely comfortable working without it. This way you don't accidentally skip a step and mess something up in the process.  

## 1. Set Up your Development Environment  
You should only need to complete this step once.  

**1.1.** Go to the "Fork" button at the top right of the team repository. This will create a copy of the team repository on your personal GitHub account.  
**1.2.** Go to your personal GitHub profile page by going to "Your Profile" > "Repositories" > "CACI-Utility".  
**1.3.** Look for a green button that says "Clone or Download". Click the button, make sure you see "Clone with HTTPS", and copy the link provided.  
**1.4.** CD to your desired directory on your personal machine where you would like to store the project.  
```bash
cd ~/your/desired/directory  
``` 

**1.5.** Clone your personal forked repository to your local machine.  
```bash
git clone COPIED_LINK  
```

*Note: When you clone the repository to your machine, you may not be in the correct directoy. Be sure to CD into the project directoy before you continue.*

**1.6.** Connect your fork to the team repository. Currently, the repository you have cloned to your machine is independent of our team's repository. By connecting your fork to the team repository, you will be able to get code updates from the team in the future.  
```bash
git remote add upstream https://github.com/Get-Reqd/CACI-Utility.git  
```

**1.7.1.** Enter Eclipse and go to "File" > "Import...".  
**1.7.2.** In the import wizard, open the Git folder & select "Projects from Git".  
**1.7.3.** Select "Existing local repository".  
**1.7.4.** Click "Add..." and browse for the location where you cloned the project to your machine.  
**1.7.5.** After you select the project location, a repository with a check-box will appear in the results. Click the check-box and hit "Finish".  
**1.7.6.** Continue through the wizard with default settings.  
**1.7.7.** You will now see the project in your Ecplise workspace.  
**1.7.8.** Right click the newly imported project, then select "Maven" > "Update Project" > "OK".  

## 2. Update your Fork’s Master  
This is an important step because if any team members have submitted new code to the team repository, you’ll want to have their changes on your machine before you start developing.  

**2.1.** Fetch all changes from the team repository. This wont change anything on your machine yet. This just brings down all of the new changes to your machine in the background.  
```bash
git fetch upstream  
```

**2.2.** Move to your master branch.  
```bash
git checkout master  
```

**2.3.** Update your local master branch with the changes you pulled from the team repository.  
```bash
git merge upstream/master  
```

**2.4.** Update your fork's master branch with the changes you pulled from the team repository.  
```bash
git push origin master
```

## 3. Create Branch for Development.  
This step is also very important because you **NEVER** want to develop on the master branch. Avoid doing this at **ALL COSTS**. Any development on the master branch can cause confusion and could also cause this workflow to break. See **forgot to branch** in the tip section below if you find yourself accidentally working on the master branch.  

**3.1.** Checkout to your master branch.  
```bash
git checkout master  
```

**3.2.** Create a new branch for you to make your changes on. Title your branch something meaningful. For example, if you are updating the Split class to include checksums, a good name would be **split-checksum**.  
```bash
git checkout -b “your-meaningful-branch-name”  
```

## 4. Begin Development  
It is good practice to commit when you come to a good stopping point in your development. Think of committing as a well defined "checkpoint" in your development process. This way if you ever need to revert to an earlier version of your code you can easily find what point to revert back to. In this workflow, you'll want to save more often than you commit and you'll want to commit more often than you submit pull requests.  

**4.1.** Make changes to your code until you reach a reasonable stopping point that you would feel comfortable reverting back to if something goes horribly wrong later on. Then make sure you've saved your changes in Eclipse.  
**4.2.** See what files you have changed and updated. If you don't see your file has been modified, make sure you saved your changes in Eclipse then try again.  
```bash
git status  
```
**4.3.** Add your updated files to the stage. This command will add *all of your changes* to the stage.  
```bash
git add .  
```

*Tip: You can also just add individual files if you don't want to add all of your changes.*
```bash
git add path/file-name
```

**4.4.** Commit your changes using a message describing your changes in present tense. You'll want to be brief, yet descriptive here because if you ever need to revert to an earlier commit you'll want to identify which commit to revert back to.  
*I.e. "Update split class to incorporate checksums."*  
```bash
git commit -m "Meaningful message in present tense."  
```

*Tip: This command will show you a list of all your previous commits.*  
```bash
git log
```

**4.5.** Return to **step 4.1** and repeat until you are ready to submit major changes to the team.  

## 5. Prepare a Pull Request  
Pull requests are how you will submit your changes from your forked repository to the team repository. This is essentially how you will “turn in” your code changes. You'll generally only want to do this when you have finished working on the changes you were assigned.  

**5.1.** Fetch all changes from the team repository. This wont change anything on your machine yet. This just brings down all of the new changes to your machine in the background.  
```bash
git fetch upstream  
```

**5.2.** Move to your master branch.  
```bash
git checkout master  
```

**5.3.** Update your local master branch with the changes you pulled from the team repository.  
```bash
git merge upstream/master  
```

**5.4.** If you get the message *"Already up-to-date"* you can skip to **step 5.4.4**. If not, you have pulled new changes from the team repository. This means while you were working on your code, someone on the team updated the team repository with their changes. Before you submit your pull request, you want to make sure you have their changes in your code too so merging wont be so messy and confusing.  

**5.4.1.** Move to your feature branch.  
```bash
git checkout your-meaningful-branch-name  
```

**5.4.2.** Perform a rebase. This will include all team changes into your changes.  
```bash
git rebase master  
```

**5.4.3.** Since you brought in new changes into your code, you want to go ahead and test that everything is still working properly. If you need to make more changes because of the update, return to **step 4**. If not, continue.  
**5.4.4.** If everything is working properly and you are ready to submit your changes, push your changes to GitHub.  
```bash
git push --set-upstream origin **your-meaningful-branch-name**  
```

## 6. Submit your Pull Request  
This is where you will send your code to the team repository for merging, ultimately "turning in" your code to the team.  

**6.1.** Go to your personal forked repository on GitHub. (See **step 1.2** to find your personal repository page)  
**6.2.** You will notice a yellow box at the top of your repository with a green button that says "Compare & pull request". Click this button.  
**6.3.** In the write box, leave a comment explaining all of the changes you have made. This is an open conversation explaining to the team what you have changed and worked on. **If your pull request closes an issue** be sure to include the issue number in your comment. For example, if my pull request closes issue #6, I would include **closes issue #6** in my comment.  
**6.4.** Once you have left your comment, click the green button that says "Create pull request".  

## 7. Rinse and Repeat  
Now that you've submitted your pull request, [Travis CI](https://travis-ci.org/Get-Reqd/CACI-Utility) will build and test your changes automatically. If everything works properly, Jackson will merge your changes into the team repository. If anything goes wrong, you may have to make more changes. For now, visit our [issues page](https://github.com/Get-Reqd/CACI-Utility/issues) to find something new to tackle. Find an issue with a "Help Wanted" tag that you want to work on, leave a comment saying you'll take care of it, then return to **step 2** to start your work.


# Development Tips
Miscellaneous things that could be helpful while using the GitHub workflow. 

## Forgot to branch? 
You've realized you have been accidentally developing on your master branch. No problem. Let's get this fixed to get you back on track.  

If you haven't committed any changes to master, see [this answer](https://stackoverflow.com/a/22082669) to bring your current changes to the correct feature branch.  

If you have committed one or more changes to master, see [this answer](https://stackoverflow.com/a/36463546) to reset your master branch and move your commits to the correct feature branch.  
