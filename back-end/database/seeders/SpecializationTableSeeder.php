<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use DB;

class SpecializationTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('specializations')->insert([
            'specialization' => 'IAT',
        ]);

        DB::table('specializations')->insert([
            'specialization' => 'BDAM',
        ]);

        DB::table('specializations')->insert([
            'specialization' => 'SE',
        ]);

        DB::table('specializations')->insert([
            'specialization' => 'FICT',
        ]);
    }
}
