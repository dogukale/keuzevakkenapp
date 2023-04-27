<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Review;
use DB;

class ReviewController extends Controller
{
    public function index() {
        return Review::all();
    }

    public function show($class_id){
        return Review::all()->where("class_id", "==", $class_id);
    }
    
    /*
    |--------------------------------------------------------------------------
    | Geen Laravel functienamen
    |--------------------------------------------------------------------------
    */

    public function showClassPeriod($class_id, $period) {
        return Review::all()->where("class_id", "==", $class_id)->where('period', "==", $period);
    }

    public function showClassUser($student_id) {
        return Review::all()->where("student_id", "==", $student_id);
    }


    /*
    |--------------------------------------------------------------------------
    | Sanctum beveiligde functie
    |--------------------------------------------------------------------------
    */

    public function makeReview(Request $request) {

        $inschrijving_bestaat = Review::all()->where('user_id', "==", $request->user_id)
        ->where("class_code", "==", $request->class_code)->count();

        // Als de gebruiker al is ingeschreven, wordt er niks naar de table ge-insert
        if ($inschrijving_bestaat > 0) {
            return "Je staat al ingeschreven voor dit keuzevak";
        }

        else{
            DB::table('review')->insert([
                'user_id' => $request->user_id,
                'student_name' => $request->student_name,
                'class_name' => $request->class_name,
                'class_code' => $request->class_code,
                'specialization' => $request->class_spec,
                'description' => $request->class_desc,
            ]);
    
            return "Je staat nu ingeschreven voor dit keuzevak";
        } 
    }


    public function getUserProfile(Request $request) {

        $user_id = auth()->user()->id;

        $user_vakken = Review::all()->where('user_id', '==', $user_id);

        return $user_vakken;
    }
}
