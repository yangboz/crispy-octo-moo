//
//  EventsViewController.h
//  CrispyOctoMoo
//
//  Created by yangboz on 12/7/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Snap415TaxEvent.h"
#import "Snap415Model.h"
#import "TableViewCell4WebObject.h"

@interface EventsViewController : UITableViewController

@property (strong,nonatomic) NSArray *taxEventsResult;

@end

