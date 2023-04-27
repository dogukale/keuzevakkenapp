<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

use App\Http\Controllers\UserController;
use App\Http\Controllers\ReviewController;
use App\Http\Controllers\Auth\LoginController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

// Route::middleware('auth:api')->get('/user', function (Request $request) {
//     return $request->user();
// });

/*
|--------------------------------------------------------------------------
| User Controllers
|--------------------------------------------------------------------------
*/
Route::get('/users', [UserController::class, 'index']);
Route::get('/users/{student_id}', [UserController::class, 'show']);
Route::get('/specialization/{specialization}', [UserController::class, 'showSpecialization']);

Route::post('/login', [LoginController::class, 'login']);

Route::group(['middleware' => ['auth:sanctum']], function ($route) {
    $route->get('/user', [LoginController::class, 'isLoggedIn']);
    $route->post('/logout', [LogoutController::class, 'logOut']);
    $route->post('/makereview', [ReviewController::class, 'makeReview']);
    $route->get('/profilepage', [ReviewController::class, 'getUserProfile']);
});


/*
|--------------------------------------------------------------------------
| Request Controllers
|--------------------------------------------------------------------------
*/

Route::get('/review/class', [ReviewController::class, 'index']);
Route::get('/review/class/{class_id}', [ReviewController::class, 'show']);
Route::get('/review/class/{class_id}/{period}', [ReviewController::class, 'showClassPeriod']);
Route::get('/review/user/{student_id}', [ReviewController::class, 'showClassUser']);
