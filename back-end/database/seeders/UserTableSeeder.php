<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\Hash;
use DB;

class UserTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $student_nummer = '1123069';
        DB::table('users')->insert([
            'student_id' => $student_nummer,
            'name' => 'Hasan Ekinci',
            'email' => 's'. $student_nummer . '@student.hsleiden.nl',
            'password' => Hash::make('keuzevakken'),
            'specialization' => 'IAT',
        ]);


        $student_nummer = '1122842';
        DB::table('users')->insert([
            'student_id' => $student_nummer,
            'name' => 'Dogukan Kalemli',
            'email' => 's'. $student_nummer . '@student.hsleiden.nl',
            'password' => Hash::make('keuzevakken'),
            'specialization' => 'IAT',
        ]);


        $student_nummer = '1122788';
        DB::table('users')->insert([
            'student_id' => $student_nummer,
            'name' => 'Ebubekir Elmaagac',
            'email' => 's'. $student_nummer . '@student.hsleiden.nl',
            'password' => Hash::make('keuzevakken'),
            'specialization' => 'BDAM',
        ]);

    }
}
