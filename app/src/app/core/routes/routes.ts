import { Routes } from "@angular/router";

export const projectRoutes: Routes = [
    {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },
    {
        path: 'dashboard',
        loadChildren: () => import('../../dashboard/dashboard.module').then(m => m.DashboardModule)
    },
    {
        path: 'deposits',
        loadChildren: () => import('../../deposits/deposits.module').then(m => m.DepositsModule)
    },
    {
        path: 'withdrawls',
        loadChildren: () => import('../../withdrawls/withdrawls.module').then(m => m.WithdrawlsModule)
    }
]