//
//  AccountSettingsViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/13/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "AccountSettingsViewController.h"

@interface AccountSettingsViewController ()

@end

@implementation AccountSettingsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    //Loading begin
    [MBProgressHUD showHUDAddedTo:self.view animated:YES];
    //If you need to run your long-running task in the main thread, you should perform it with a slight delay, so UIKit will have enough time to update the UI (i.e., draw the HUD) before you block the main thread with your task.
    dispatch_time_t popTime = dispatch_time(DISPATCH_TIME_NOW, 0.01 * NSEC_PER_SEC);
    dispatch_after(popTime, dispatch_get_main_queue(), ^(void){
        /*
         //NSOperationQueue
         operationQueue = [NSOperationQueue new];
         //[operationQueue setMaxConcurrentOperationCount:1];
         //Create a new NSOpeartion object using NSInvocationOperation subclass
         //Tell it to run the task_createCateItemId method.
         NSInvocationOperation *operation_createCateItemId = [[NSInvocationOperation alloc] initWithTarget:self selector:@selector(task_createCateItemId) object:nil];
         //Add the operation to the queue and let it to be executed.
         [operationQueue addOperation:operation_createCateItemId];
         [operation_createCateItemId release];
         //The same story as above,just tell here to execute task_uploadCateItemPhoto;
         NSInvocationOperation *operation_uploadCateItemPhoto = [[NSInvocationOperation alloc] initWithTarget:self selector:@selector(task_uploadCateItemPhoto) object:nil];
         [operationQueue addOperation:operation_uploadCateItemPhoto];
         [operation_uploadCateItemPhoto addDependency:operation_createCateItemId];
         [operation_uploadCateItemPhoto release];
         //
         //The same story as above,just tell here to execute task_addCateItem;
         NSInvocationOperation *operation_addCateItem = [[NSInvocationOperation alloc] initWithTarget:self selector:@selector(task_addCateItem) object:nil];
         [operationQueue addOperation:operation_addCateItem];
         [operation_addCateItem addDependency:operation_uploadCateItemPhoto];
         [operation_addCateItem release];
         //
         //The same story as above,just tell here to execute task_reviewCateItem;
         NSInvocationOperation *operation_reviewCateItem = [[NSInvocationOperation alloc] initWithTarget:self selector:@selector(task_reviewCateItem) object:nil];
         [operationQueue addOperation:operation_reviewCateItem];
         [operation_reviewCateItem addDependency:operation_addCateItem];
         [operation_reviewCateItem release];
         */
        //Delegate to Snap415API.
        [[Snap415API sharedInstance] getIncomeCategories];
        [[Snap415API sharedInstance] getFilingCategories];
        [[Snap415API sharedInstance] getChildrenCategories];
        [[Snap415API sharedInstance] getChildrenKeywords];
        [[Snap415API sharedInstance] getMortgageInterests];
        [[Snap415API sharedInstance] getEVCredits];
        //Loading end
        [MBProgressHUD hideHUDForView:self.view animated:YES];
        //Table view data source config:
        
    });
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
