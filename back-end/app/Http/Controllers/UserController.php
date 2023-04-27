<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;

class UserController extends Controller
{
    public function index(){
        return User::all();
    }

    public function show($student_id){
        return User::all()->where("student_id", "==", $student_id)->first();
    }

    /*
    |--------------------------------------------------------------------------
    | Geen Laravel functienamen
    |--------------------------------------------------------------------------
    */

    public function showSpecialization($specialization) {
        $specialization = strtoupper($specialization);
        return User::all()->where("specialization", "==", $specialization);
    }
}
