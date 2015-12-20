//
//  EventsViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/7/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "EventsViewController.h"

@interface EventsViewController ()

@end

@implementation EventsViewController

@synthesize taxEventsResult;
static NSString *CellIdentifier = @"TableViewCell4Events";

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    Snap415UserTaxEvents *snap415UserTaxEvents = [[Snap415Model sharedInstance] snap415UserTaxEvents];
    self.taxEventsResult = snap415UserTaxEvents.taxEvents;
    NSLog(@"self.taxEventsResult:%@",self.taxEventsResult.description);
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
    return [[self taxEventsResult] count];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:  (NSIndexPath *)indexPath
{
    //
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:CellIdentifier];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:CellIdentifier];
    }

//
    NSDictionary *snap415TaxEventObj= (NSDictionary *)[self.taxEventsResult objectAtIndex:indexPath.row];
    //    cell.selectionStyle = UITableViewCellStyleSubtitle;
    if (![[snap415TaxEventObj objectForKey:@"eventDescription" ] isKindOfClass:[NSNull class]])
    {
        cell.textLabel.text = [snap415TaxEventObj objectForKey:@"eventDescription"];//taxCategory
        cell.detailTextLabel.text = [snap415TaxEventObj objectForKey:@"taxCredit"];
     }
    return cell;
}

@end
