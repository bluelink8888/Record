# Record
This project create for record coding using library and project structure pass two year in eztravel.

### Project structure
It's base three layer project, controller ,service and repository. Common mvc structure.

### Serialize and archive object 
> SerializeUtils was build for redis. Because it only can save string value. 
> So we create a project for redis service.
> Main idea is object should serialize before saving, that can save memory usage in redis server. 


> LZ4ArchiveUtils was created for huge redis key. In eztravel redis was saving many bussiness data.
> Like city hotel price information, single hotel basic information etc.
> Because a city contains a lot of hotel. And that key was setting by city code, that make redis memory problem.
> I saw a single redis key has MB size value in redis. So I create this class for lower redis memory usage.
 
### After eztravel
> I saw a lot of cron job for updating data, sending report and pushing data two other Server.
> And already on the way to database read write separation.
> Using three layer project structure already not must suitable for their bussiness model.
> We often check cron job have update data successful or not, and it log always a lot unimportant information.
> When project getting bigger, it's hard to change logs' problem.
> In new project problem still exist, Maybe CQRS-ES can help them.
> And message queue will prevent database crash because huge data updating job.
 
### Reference
    My eztravel coding life.
