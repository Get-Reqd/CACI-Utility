# Get-Reqd Github Workflow  

## 1. Set Up your Development Environment  
You only need to do this step once.  

**1.1.** Fork repo to your personal account by clicking the "Fork" button at the top right of the team repo.  
**1.2.** CD to your desired directory on your personal machine.  
**1.3.** Clone your personal fork to local machine by getting the repo link from the project on your personal account.  
> git clone PERSONAL_FORK_REPO  

**1.4.** Connect your fork to the team repo  
> git remote add upstream TEAM_GIT_REPO  

## 2. Update your Fork’s Master  
This is important because if any team members have submitted pull requests to the team repository, you’ll want to have their changes on your machine before you start developing.  

**2.1.** Fetch all changes from the team repository. This wont change anything on your machine yet.  
> git fetch upstream  

**2.2.** Move to your master branch.  
> git checkout master  

**2.3.** Update your local master branch with the changes you pulled from the team repository.  
> git merge upstream/master  

## 3. Create Branch for Development.  
This is very important because you **NEVER** want to develop on the master branch. Avoid doing this at **ALL COSTS**. Any development on the master branch can cause this workflow to break.  

**3.1.** Checkout to your master branch.  
> git checkout master  

**3.2.** Create a new branch for you to make your changes on. Title your branch to something meaningful. For example, if you are updating the Split class to include checksums, a good name would be **split-checksum**.  
> git checkout -b “FEATURE_BRANCH_NAME”  

## 4. Begin Development  
It is good practice to commit when you come to a good stopping point. Think of committing as another way to save your progress. This way if you ever need to revert to an earlier version of your code you can easily find what point to revert back to.  

**4.1.** Make changes to your code until you reach a reasonable stopping point.  
**4.2.** See what files you have changed and updated.  
> git status  

**4.3.** Add your updated files to the stage. This command will add all of your changes to the stage.  
> git add .  

**4.4.** Commit your changes using a message describing your changes in present tense.  
*I.e. "Update split class to incorporate checksums."*  
> git commit -m "Meaningful message in present tense."  

**4.5.** Return to **step 4.1** until you are ready to submit major changes to the team.  

## 5. Prepare a Pull Request  
Pull requests are how you will submit your changes from your forked repository to the team repository. This is essentially how you will “turn in” your code changes.  

**5.1.** Fetch all changes from the team repository. This wont change anything on your machine yet.  
> git fetch upstream  

**5.2.** Move to your master branch.  
> git checkout master  

**5.3.** Update your local master branch with the changes you pulled from the team repository.  
> git merge upstream/master  

**5.4.** If you get the message *"Already up to date"* you can skip to **step 5.4.4**. If not, you have pulled new changes from the team repository. This means while you were working on your code, someone on the team updated the team repository with their changes. Before you submit your pull request, you want to make sure you have their changes in your code too.  

**5.4.1.** Move to your feature branch.  
> git checkout FEATURE_BRANCH_NAME  

**5.4.2.** Perform a rebase. This will bring the new changes into your code.  
> git rebase master  

**5.4.3.** Since you brought in new changes into your code, you want to go ahead and test that everything is working properly. If you need to make more changes because of this update, return to **step 4**.  
**5.4.4.** If everything is working properly and you are ready to submit your changes, push the changes to GitHub.  
> git push --set-upstream origin FEATURE_BRANCH_NAME  

## 6. Submit your Pull Request  
Go to the forked repo on your personal Github account. You will notice a prompt at the top of the repository asking if you want to submit a pull request. Follow the prompt and be sure to detail your changes for the team to see in the description.  


## 7. Rinse and Repeat  
Now that you've submitted your pull request, Jackson will merge your code into our project. Return to **step 2** and start working on something new.  
