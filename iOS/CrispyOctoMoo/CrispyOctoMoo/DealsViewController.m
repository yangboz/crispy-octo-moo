//
//  DealsViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/12/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "DealsViewController.h"

@interface DealsViewController ()

@end

@implementation DealsViewController

@synthesize sqootDealObjectsResult;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
    //Variables init
    
    //SqootDeal Object popup for detail view.
//    STPopupController *popupController = [[STPopupController alloc] initWithRootViewController:[[UIStoryboard storyboardWithName:@"Main" bundle:nil] instantiateViewControllerWithIdentifier:@"SocialLoginPopupViewController"]];
//    popupController.cornerRadius = 4;
//    [popupController presentInViewController:self];
    // Do any additional setup after loading the view, typically from a nib.
    SqootDealsObject *sqootDealsObject = [[Snap415Model sharedInstance] sqootDealsObject];
    self.sqootDealObjectsResult = [sqootDealsObject deals];
    NSLog(@"self.sqootDealObjectsResult:%@",self.sqootDealObjectsResult.description);
    //
    [self.tableView reloadData];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    //    NSLog(@"self overviewsResult,count:%d",[[self overviewsResult] count]);
    return [[self sqootDealObjectsResult] count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:  (NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"ReuseCellName_deals";
//    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:CellIdentifier];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
//    if (cell == nil) {
//        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:CellIdentifier];
//    }
    cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    //
    NSDictionary *sqootDealObjDict= (NSDictionary *)[self.sqootDealObjectsResult objectAtIndex:indexPath.row];
    NSDictionary *sqootDealObj = [sqootDealObjDict objectForKey:@"deal"];
    
    cell.selectionStyle = UITableViewCellStyleSubtitle;
    cell.textLabel.text = [sqootDealObj objectForKey:@"short_title"];
    cell.detailTextLabel.text = [sqootDealObj objectForKey:@"title"];
//    cell.badgeString = [sqootDealObj objectForKey:@"discount_amount"];
//    if(cell.badgeString)
    
    return cell;
}



#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
    if ([[segue identifier] isEqualToString:@"showDetail"]) {
        DetailDealViewController *detailViewController = [segue destinationViewController];
        
        detailViewController.dealData = [sqootDealObjectsResult objectAtIndex:[self.tableView indexPathForSelectedRow].row];
        NSLog(@"detailViewController.dealData:%@",detailViewController.dealData.description);
    }
}


@end
