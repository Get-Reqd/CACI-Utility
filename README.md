# Get-Reqd Github Workflow

## 1. Set up your development environment.  
You only need to do this step once.  

1.1. Fork repo to your personal account by clicking the "Fork" button at the top right of the team repo.  
1.2. CD to your desired directory  
1.3. Clone your personal fork to local machine by getting the repo link from the project on your personal account.  
> git clone PERSONAL_FORK_REPO  

1.4. Connect your fork to the team repo  
> git remote add upstream TEAM_GIT_REPO  

## 2. Update your fork’s master.  
This is important because if any team members have submitted pull requests to the team repository, you’ll want to have their changes on your machine before you start developing.  

> git fetch upstream  
> git checkout master  
> git merge upstream/master  

## 3. Create branch to develop.  
This is very important because you NEVER want to develop on the master branch. Avoid doing this at ALL COSTS. Any development on the master branch can cause this workflow to break.  

> git checkout master  
> git checkout -b “FEATURE_BRANCH_NAME”  

## 4. Begin development  
It is good practice to commit when you come to a good stopping point. Think of committing as another way to save your progress. This way if you ever need to revert to an earlier version of your code you can find when to revert to.  

*How to commit*  
See what files you have changed and updated  
> git status

Add your updated files to the stage  
> git add .  

Commit your changes using present tense.  
I.e. git commit -m "Update split class to incorporate checksums."  
> git commit -m "Meaningful message in present tense."  

## 5. Submit a pull request  
Pull requests are how you will submit your changes from your forked repository to the team repository. This is essentially how you will “turn in” your code changes.  
> git fetch upstream  
> git checkout master  
> git merge upstream/master  
    If there are any new changes to master, perform the following command. If any team members added changes to the team repository while you were working on your code change, this will add their changes to your project as well. This will make merging into the team repository much less messy.
        > git checkout FEATURE_BRANCH_NAME
        > git rebase master
        Re test your code to ensure everything is working properly. If any bugs exist fix the bugs and repeat step 5.
    > git push --set-upstream origin FEATURE_BRANCH_NAME
6. Go to the forked repo on your personal Github account and submit a pull request
7. Go back to step 2 to start something new.
