<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Models\Product;
use App\Http\Controllers\ProductController;

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

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
//retrieve csv files (Get All data from database)
Route::get('/contacts', [ProductController::class,'index']);

//create data
Route::post('/contacts',  function (Request $request) {
    $request->validate([
        'name'=>'required',
        'description'=>'required',
        'slug'=>'required',
        'price'=>'required'
    ]);
   return Product::create($request->all());
});

//getById data and edit the data
Route::get('/contacts/{id}', function (Request $request, $id) {
    $product=Product::find($id);
    $product->update($request->all());
    return $product;
});

//delete data
Route::delete('/contacts/{id}', function ($id) {
    return Product::destroy($id);
});